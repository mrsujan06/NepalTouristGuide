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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplicationContext()).getAppComponent().inject(this);
        mCampingPresenter.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(CampingActivity.this));

        String place_Name = (String) getIntent().getExtras().getSerializable("cityname");
        getCampingSpotsByPlace(place_Name);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_camping;
    }

    private void getCampingSpotsByPlace(String placeName){
        mCampingPresenter.listAllCampingSpots(placeName, API_KEY);
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
