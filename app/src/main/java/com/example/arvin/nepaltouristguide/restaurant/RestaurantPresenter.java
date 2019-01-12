package com.example.arvin.nepaltouristguide.restaurant;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RestaurantPresenter extends BasePresenter<RestaurantView> {

    private static final String TAG = "ERROR MESSAGE";

    @Inject
    public RestaurantPresenter(ApiNepalServiceInteractor apiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = apiNepalServiceInteractor;
    }

    @SuppressLint("CheckResult")
    public void listAllRestaurants(String query, String key) {

        query = "Restaurants+in+" + query.toUpperCase();
        getApiNepalServiceInteractor().getRestaurants(query, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse>() {

                    @Override
                    public void accept(ApiResponse apiResponse) throws Exception {

                        if (getMvpView() != null) {
                            try {
                                getMvpView().onFetchDataSuccess(apiResponse);
                            } catch (Exception e) {
                                Log.i(TAG, e.getMessage());
                            }
                        }
                        Log.d("Success Message", " Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onFetchDataError(throwable.getMessage());
                    }
                });

        getMvpView().onFetchDataProgress();
    }
}
