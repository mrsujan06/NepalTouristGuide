package com.example.arvin.nepaltouristguide.mountain;

import com.example.arvin.nepaltouristguide.base.BaseVisitNepalAdapter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public class MountainAdapter extends BaseVisitNepalAdapter {

    private final OnMountainSelectedInterface mListener;

    public MountainAdapter(ApiResponse mApiResponse, OnMountainSelectedInterface mListener) {
        super(mApiResponse);
        this.mListener = mListener;
    }

    @Override
    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
        mListener.onMountainSelected(index, mApiResponse);
    }
}
