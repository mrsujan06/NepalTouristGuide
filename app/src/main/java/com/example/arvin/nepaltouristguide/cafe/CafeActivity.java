package com.example.arvin.nepaltouristguide.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.cafe_detail.CafeDetailActivity;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.place_options.PlacesOptionsActivity;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.arvin.nepaltouristguide.Constants.API_KEY;

public class CafeActivity extends BaseActivity implements CafeView, OnCafeSeletedInterface {

    public static final String CAFE_ID = "cafe_id";

    @BindView(R.id.cafeList)
    RecyclerView mRecyclerView;

    @Inject
    CafePresenter mCafePresenter;

    private CafeAdapter mCafeAdapter;
    String place_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(CafeActivity.this);
        mCafePresenter.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(CafeActivity.this));

        place_name = (String) getIntent().getExtras().getSerializable(PlacesOptionsActivity.CITY_NAME);
        getCafeByPlace(place_name);
    }

    private void getCafeByPlace(String place_name) {
        mCafePresenter.getData(place_name, API_KEY);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_cafe;
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        OnCafeSeletedInterface listener = CafeActivity.this;
        mCafeAdapter = new CafeAdapter(response, listener);
        mRecyclerView.setAdapter(mCafeAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on Restaurant Activity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void onCafeSelected(int index, ApiResponse mApiResponse) {
        Toast.makeText(this, "Cafe button clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(App.getInstance().getContext(), CafeDetailActivity.class);
        intent.putExtra(CAFE_ID, mApiResponse.getResults().get(index).getPlaceId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mCafePresenter.unbind();
        mCafePresenter.dispose();
        super.onDestroy();
    }

}
