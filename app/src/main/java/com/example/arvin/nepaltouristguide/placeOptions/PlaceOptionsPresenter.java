package com.example.arvin.nepaltouristguide.placeOptions;

import android.content.Context;
import android.content.Intent;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;

import javax.inject.Inject;

public class PlaceOptionsPresenter extends BasePresenter<PlaceOptionView> {

    @Inject
    public PlaceOptionsPresenter(ApiNepalServiceInteractor apiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = apiNepalServiceInteractor;
    }
}
