package com.example.arvin.nepaltouristguide.restaurant_detail;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;

public interface RestaurantDetailView extends MvpView {
    void onFetchDataProgress();

    void onFetchDataSuccess(PlaceDetailResponse response);

    void onFetchDataError(String error);
}
