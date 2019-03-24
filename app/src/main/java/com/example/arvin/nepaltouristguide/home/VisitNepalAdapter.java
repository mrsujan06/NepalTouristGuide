package com.example.arvin.nepaltouristguide.home;

import com.example.arvin.nepaltouristguide.base.BaseRecyclerViewAdapter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public class VisitNepalAdapter extends BaseRecyclerViewAdapter {

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
