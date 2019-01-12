package com.example.arvin.nepaltouristguide.restaurant;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;

public class RestaurantActivity extends BaseActivity implements RestaurantView {

    @BindView(R.id.restaurantRV)
    RecyclerView mRecyclerView;
    @Inject
    RestaurantPresenter mRestaurantPresenter;
    RestaurantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_activity);

        ((App) getApplication()).getAppComponent().inject(RestaurantActivity.this);
        mRestaurantPresenter.bind(this);

        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RestaurantActivity.this));

        String place_name = (String) getIntent().getExtras().getSerializable("cityname");
        mRestaurantPresenter.listAllRestaurants("Restaurants+in+" + place_name, API_KEY);
    }


    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        mAdapter = new RestaurantAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on Restaurant Activity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        mRestaurantPresenter.unbind();
        super.onDestroy();
    }
}
