package com.example.arvin.nepaltouristguide.restaurant;


import com.example.arvin.nepaltouristguide.base.BaseVisitNepalAdapter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public class RestaurantAdapter extends BaseVisitNepalAdapter {

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
