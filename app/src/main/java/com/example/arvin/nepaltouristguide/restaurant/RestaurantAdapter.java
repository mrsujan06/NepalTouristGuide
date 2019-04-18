package com.example.arvin.nepaltouristguide.restaurant;


import com.example.arvin.nepaltouristguide.base.BaseAdapter;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;

public class RestaurantAdapter extends BaseAdapter {

    private final OnRestaurantSelectedInterface mListener;

    public RestaurantAdapter(ApiResponse mApiResponse, OnRestaurantSelectedInterface mListener) {
        super(mApiResponse);
        this.mListener = mListener;
    }

    @Override
    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
        mListener.onRestaurantSelected(index, mApiResponse);
    }
}
