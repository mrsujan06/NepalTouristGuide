package com.example.arvin.nepaltouristguide.cafe_detail;

import android.os.Bundle;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.Constants;
import com.example.arvin.nepaltouristguide.cafe.CafeActivity;
import com.example.arvin.nepaltouristguide.detail_base.BaseDetailActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class CafeDetailActivity extends BaseDetailActivity implements CafeDetailView {

    @Inject
    CafeDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(this);
        mPresenter.bind(this);
        ButterKnife.bind(this);

        String cafe_id = (String) getIntent().getExtras().getSerializable(CafeActivity.CAFE_ID);
        getPlaceDetails(cafe_id);
    }

    @Override
    public void getPlaceDetails(String restaurant_id) {
        mPresenter.getPlaceDetail(restaurant_id, Constants.API_KEY);
    }

}
