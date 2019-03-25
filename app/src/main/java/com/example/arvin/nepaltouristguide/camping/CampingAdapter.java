package com.example.arvin.nepaltouristguide.camping;

import com.example.arvin.nepaltouristguide.base.BaseAdapter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public class CampingAdapter extends BaseAdapter {

    OnCampingSelectedInterface mListener;

    public CampingAdapter(ApiResponse mApiResponse, OnCampingSelectedInterface mListener) {
        super(mApiResponse);
        this.mListener = mListener;
    }

    @Override
    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
        mListener.onCampingSelected(index, mApiResponse);

    }
}
