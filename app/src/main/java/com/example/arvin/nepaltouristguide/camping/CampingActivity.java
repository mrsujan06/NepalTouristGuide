package com.example.arvin.nepaltouristguide.camping;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CampingActivity extends BaseActivity implements CampingView {

    @BindView(R.id.campingRV)
    RecyclerView mRecyclerView;
    @Inject
    CampingPresenter mCampingPresenter;
    CampingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping);

        ((App) getApplicationContext()).getAppComponent().inject(this);
        mCampingPresenter.bind(this);

        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CampingActivity.this));

        String place_name = (String) getIntent().getExtras().getSerializable("cityname");
        mCampingPresenter.listAllCampingSpots("Camping+in+" + place_name.toUpperCase(), "AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc");
    }

    @Override
    protected void onDestroy() {
        mCampingPresenter.unbind();
        super.onDestroy();
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
}
