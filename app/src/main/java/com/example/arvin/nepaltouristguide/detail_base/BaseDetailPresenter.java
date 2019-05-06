package com.example.arvin.nepaltouristguide.detail_base;

import android.graphics.Color;

import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.service.Interactor.ApiNepalServiceInteractor;
import com.taufiqrahman.reviewratings.BarLabels;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseDetailPresenter<V extends DetailMvpView> {

    protected CompositeDisposable bag = new CompositeDisposable();
    protected ApiNepalServiceInteractor mApiNepalServiceInteractor;
    private V mMvpView;

    public void bind(V view) {
        this.mMvpView = view;
    }

    public void unbind() {
        mMvpView = null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    protected ApiNepalServiceInteractor getApiNepalServiceInteractor() {
        return mApiNepalServiceInteractor;
    }

    public abstract void getPlaceDetail(String placeId, String apiKey);

    public void updateToolbarName(String placeName) {
        if (placeName != null && !placeName.isEmpty()) {
            getMvpView().setToolbarName(placeName);
        }
    }

    public void updatePlaceName(String placeName) {
        if (placeName != null && !placeName.isEmpty()) {
            getMvpView().setPlaceName(placeName);
        }
    }

    public void updatePlaceAddress(String placeAddress) {
        if (placeAddress != null) {
            getMvpView().setPlaceAddress(placeAddress);
        }
    }

    public void updatePlacePhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            getMvpView().setPlacePhoneNumber(phoneNumber);
        }
    }

    public void updateOpenCloseStatus(Boolean isOpen) {
        if (isOpen != null) if (isOpen) {
            getMvpView().setPlaceOpeningStatus(true);
        } else {
            getMvpView().setPlaceOpeningStatus(false);
        }
    }

    public void updateOpeningTimes(List<String> weekDayText) {

        if (weekDayText != null && !weekDayText.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String items : weekDayText) {
                builder.append(items).append("\n");
            }
            getMvpView().setOpeningTimes(String.valueOf(builder));
        }
    }

    public void updateRating(Double rating) {
        if (rating != null) {
            getMvpView().setRating(String.valueOf(rating));
        }
    }

    public void updateRatingBar(Double rating) {
        if (rating != null) {
            getMvpView().setRatingBar(rating.intValue());
        }
    }

    public void updateUserRatingTotal(String totalRating) {
        if (totalRating != null) {
            getMvpView().setUserRatingTotal(totalRating);
        }
    }

    public void updateRatingBarChart() {
        int[] colors = new int[]{
                Color.parseColor("#FFDF00"),
                Color.parseColor("#FFDF00"),
                Color.parseColor("#FFDF00"),
                Color.parseColor("#FFDF00"),
                Color.parseColor("#FFDF00")};

        int[] raters = new int[]{80, 65, 55, 40, 10};

        getMvpView().setRatingChart(100, BarLabels.STYPE1, colors, raters);
    }

    public void updateUserComments(PlaceDetailResponse response) {
        if (response != null) {
            getMvpView().setUserComment(response);
        }
    }

    public void updateLocation(Double latitude, Double longitude) {
        getMvpView().setLocation(latitude.toString(), longitude.toString());
    }

    public void dispose() {
        if (!bag.isDisposed()) {
            bag.clear();
        }
    }
}
