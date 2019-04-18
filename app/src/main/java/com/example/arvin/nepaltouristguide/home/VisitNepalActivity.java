package com.example.arvin.nepaltouristguide.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.place_options.PlacesOptionsActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class VisitNepalActivity extends BaseActivity implements VisitNepalView, OnCitySelectedInterface {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @Inject
    VisitNepalPresenter mVisitNepalPresenter;

    private VisitNepalAdapter mAdapter;

    public static final String PLACE_PHOTO = "place_photo";
    public static final String PLACE_NAME = "place_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(this);
        setSupportActionBar(mToolbar);
        mVisitNepalPresenter.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getTopCitiesInNepal();

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_visit_nepal;
    }

    private void getTopCitiesInNepal() {
        mVisitNepalPresenter.getTopCitiesInNepal();
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        OnCitySelectedInterface mListener = VisitNepalActivity.this;
        mAdapter = new VisitNepalAdapter(response, mListener);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on VisitNepalActivity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void onCitySelected(int index, ApiResponse mApiResponse) {
        Intent intent = new Intent(App.getInstance().getContext(), PlacesOptionsActivity.class);
        intent.putExtra(PLACE_PHOTO, mApiResponse.getResults().get(index).getPhotos().get(0).getPhotoReference());
        intent.putExtra(PLACE_NAME, mApiResponse.getResults().get(index).getName());
        startActivity(intent);
        Toast.makeText(this, mApiResponse.getResults().get(index).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.visitnepal_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVisitNepalPresenter.unbind();
    }
}
