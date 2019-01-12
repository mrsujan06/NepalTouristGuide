package com.example.arvin.nepaltouristguide.camping;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;

public class CampingActivity extends BaseActivity implements CampingView {

    @BindView(R.id.campingRV)
    RecyclerView mRecyclerView;

    @Inject
    CampingPresenter mCampingPresenter;

    private CampingAdapter mAdapter;
    private static final String apiKeyCampingSpot = API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping);

        ((App) getApplicationContext()).getAppComponent().inject(this);
        mCampingPresenter.bind(this);

        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CampingActivity.this));

        String place_name = (String) getIntent().getExtras().getSerializable("cityname");
        mCampingPresenter.listAllCampingSpots(place_name, apiKeyCampingSpot);
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        mAdapter = new CampingAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on Camping Activity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }


    @Override
    protected void onDestroy() {
        mCampingPresenter.unbind();
        super.onDestroy();
    }

}
