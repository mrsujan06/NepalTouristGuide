package com.example.arvin.nepaltouristguide.mountain;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.service.Interactor.ApiNepalServiceInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MountainPresenter extends BasePresenter<MountainView> {

    private static final String TAG = "ERROR MESSAGE";

    @Inject
    public MountainPresenter(ApiNepalServiceInteractor apiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = apiNepalServiceInteractor;
    }

    @SuppressLint("CheckResult")
    void listOfMountain(String query, String key) {

        query = "Mountains+in+nepal";
        getApiNepalServiceInteractor().getData(query , key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {

                    if (getMvpView() != null) {
                            getMvpView().onFetchDataSuccess(apiResponse);
                    }
                    Log.d("Success Message", " Success");
                }, throwable -> Log.e(TAG, throwable.getMessage()));

        getMvpView().onFetchDataProgress();
    }
}
