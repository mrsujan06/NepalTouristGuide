package com.example.arvin.nepaltouristguide.home;

import com.example.arvin.nepaltouristguide.base.BaseAdapter;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;

public class VisitNepalAdapter extends BaseAdapter {

    OnCitySelectedInterface mListener;

    public VisitNepalAdapter(ApiResponse mApiResponse, OnCitySelectedInterface mListener) {
        super(mApiResponse);
        this.mListener = mListener;
    }

    @Override
    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
        mListener.onCitySelected(index, mApiResponse);
    }
}
