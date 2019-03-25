package com.example.arvin.nepaltouristguide.restaurant;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.placeOptions.PlacesOptionsActivity;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;

public class RestaurantActivity extends BaseActivity implements RestaurantView, OnRestaurantSelectedInterface {

    @BindView(R.id.restaurantRV)
    RecyclerView mRecyclerView;

    @Inject
    RestaurantPresenter mRestaurantPresenter;

    private RestaurantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(RestaurantActivity.this);
        mRestaurantPresenter.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(RestaurantActivity.this));

        String place_name = (String) getIntent().getExtras().getSerializable(PlacesOptionsActivity.CITY_NAME);
        getResturantsByPlace(place_name);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_restaurant;
    }

    private void getResturantsByPlace(String place_name) {
        mRestaurantPresenter.listAllRestaurants(place_name, API_KEY);
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        OnRestaurantSelectedInterface listener = RestaurantActivity.this;
        mAdapter = new RestaurantAdapter(response, listener);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on Restaurant Activity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void onRestaurantSelected(int index, ApiResponse mApiResponse) {
        Toast.makeText(this, mApiResponse.getResults().get(index).getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        mRestaurantPresenter.unbind();
        super.onDestroy();
    }
}
