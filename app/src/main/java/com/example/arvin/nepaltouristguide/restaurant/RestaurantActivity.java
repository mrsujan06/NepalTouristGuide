package com.example.arvin.nepaltouristguide.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.home.VisitNepalActivity;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.place_options.PlacesOptionsActivity;
import com.example.arvin.nepaltouristguide.restaurant_detail.RestaurantDetailActivity;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.arvin.nepaltouristguide.service.api.ApiList.API_KEY;

public class RestaurantActivity extends BaseActivity implements RestaurantView, OnRestaurantSelectedInterface {

    public static final String RESTAURANT_NAME = "RESTAURANT_NAME";
    public static final String RESTAURANT_PHOTO = "RESTAURANT_PHOTO";
    public static final String RESTAURANT_ID = "RESTAURANT_ID";

    @BindView(R.id.restaurantRV)
    RecyclerView mRecyclerView;

    @Inject
    RestaurantPresenter mRestaurantPresenter;

    private RestaurantAdapter mAdapter;
    private String place_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(RestaurantActivity.this);
        mRestaurantPresenter.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(RestaurantActivity.this));

        place_name = (String) getIntent().getExtras().getSerializable(PlacesOptionsActivity.CITY_NAME);
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
        Intent intent = new Intent(App.getInstance().getContext(), RestaurantDetailActivity.class);
        intent.putExtra(VisitNepalActivity.PLACE_PHOTO, mApiResponse.getResults().get(index).getPhotos().get(0).getPhotoReference());
        intent.putExtra(RESTAURANT_NAME, mApiResponse.getResults().get(index).getName().toString());
        intent.putExtra(RESTAURANT_ID, mApiResponse.getResults().get(index).getPlaceId());
        startActivity(intent);
        Toast.makeText(this, mApiResponse.getResults().get(index).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mRestaurantPresenter.unbind();
        super.onDestroy();
    }
}
