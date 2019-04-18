package com.example.arvin.nepaltouristguide.home;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;


public interface VisitNepalView extends MvpView {

    void onFetchDataProgress();

    void onFetchDataSuccess(ApiResponse response);

    void onFetchDataError(String error);
}
