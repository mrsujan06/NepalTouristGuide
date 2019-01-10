package com.example.arvin.nepaltouristguide.mountain;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

public interface MountainView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(ApiResponse response);
    void onFetchDataError(String error);
}
