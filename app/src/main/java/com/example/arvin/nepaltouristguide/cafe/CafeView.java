package com.example.arvin.nepaltouristguide.cafe;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;

public interface CafeView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(ApiResponse response);
    void onFetchDataError(String error);
}
