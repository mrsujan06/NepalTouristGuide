package com.example.arvin.nepaltouristguide.cashMachine;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public interface CashMachineView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(ApiResponse response);
    void onFetchDataError(String error);
}
