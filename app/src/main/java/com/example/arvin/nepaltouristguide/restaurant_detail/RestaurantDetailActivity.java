package com.example.arvin.nepaltouristguide.restaurant_detail;

import android.os.Bundle;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.Constants;
import com.example.arvin.nepaltouristguide.detail_base.BaseDetailActivity;
import com.example.arvin.nepaltouristguide.detail_base.DetailMvpView;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;

import javax.inject.Inject;

public class RestaurantDetailActivity extends BaseDetailActivity implements DetailMvpView {
    @Inject
    RestaurantDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(this);
        mPresenter.bind(this);

        String restaurant_id = (String) getIntent().getExtras().getSerializable(RestaurantActivity.RESTAURANT_ID);
        getPlaceDetails(restaurant_id);
    }

    @Override
    public void getPlaceDetails(String restaurant_id) {
        mPresenter.getPlaceDetail(restaurant_id, Constants.API_KEY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
        mPresenter.dispose();
    }
}
