package com.example.arvin.nepaltouristguide.detail_base;

import com.example.arvin.nepaltouristguide.base.MvpView;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;

public interface DetailMvpView extends MvpView {

    void onFetchDataProgress();

    void onFetchDataSuccess(PlaceDetailResponse response);

    void onFetchDataError(String error);

    void setToolbarName(String placeName);

    void setPlaceName(String placeName);

    void setPlaceAddress(String placeAddress);

    void setPlacePhoneNumber(String phoneNumber);

    void setPlaceOpeningStatus(Boolean isOpen);

    void setOpeningTimes(String weekDayText);

    void setRating(String rating);

    void setRatingBar(int rating);

    void setUserRatingTotal(String totalRating);

    void setRatingChart(int maxBar, String[] barType, int[] colors, int[] raters);

    void setUserComment(PlaceDetailResponse response);

    void setLocation(String latitude, String longitude);
}
