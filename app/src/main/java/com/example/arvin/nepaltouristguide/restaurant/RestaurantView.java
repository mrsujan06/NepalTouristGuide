package com.example.arvin.nepaltouristguide.restaurant;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public interface RestaurantView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(ApiResponse response);
    void onFetchDataError(String error);
}
