package com.example.arvin.nepaltouristguide.restaurant;


import com.example.arvin.nepaltouristguide.base.BaseRecyclerViewAdapter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public class RestaurantAdapter extends BaseRecyclerViewAdapter {

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
