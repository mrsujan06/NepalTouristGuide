package com.example.arvin.nepaltouristguide.cashMachine;

import com.example.arvin.nepaltouristguide.base.BaseVisitNepalAdapter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public class CashMachineAdapter extends BaseVisitNepalAdapter {

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
