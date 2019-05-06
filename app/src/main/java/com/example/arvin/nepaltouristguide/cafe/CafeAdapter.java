package com.example.arvin.nepaltouristguide.cafe;

import com.example.arvin.nepaltouristguide.base.BaseAdapter;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;

public class CafeAdapter extends BaseAdapter {

    private final OnCafeSeletedInterface mListener;

    public CafeAdapter(ApiResponse mApiResponse, OnCafeSeletedInterface mListener) {
        super(mApiResponse);
        this.mListener = mListener;
    }

    @Override
    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
        mListener.onCafeSelected(index, mApiResponse);
    }
}