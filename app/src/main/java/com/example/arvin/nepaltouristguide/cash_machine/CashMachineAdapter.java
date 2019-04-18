package com.example.arvin.nepaltouristguide.cash_machine;

import com.example.arvin.nepaltouristguide.base.BaseAdapter;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;

public class CashMachineAdapter extends BaseAdapter {

    OnCashSeletedInterface mListener;

    public CashMachineAdapter(ApiResponse mApiResponse, OnCashSeletedInterface mListener) {
        super(mApiResponse);
        this.mListener = mListener;
    }

    @Override
    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
        mListener.onCashSelected(index, mApiResponse);
    }
}
