package com.example.arvin.nepaltouristguide.placeOptions;

import com.example.arvin.nepaltouristguide.base.MvpView;

public interface PlaceOptionView extends MvpView {

    void getIncomingIntent();
    void setImage(String place_photo, String place_name);

}
