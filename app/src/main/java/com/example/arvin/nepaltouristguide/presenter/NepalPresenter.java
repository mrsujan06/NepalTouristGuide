package com.example.arvin.nepaltouristguide.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;
import com.example.arvin.nepaltouristguide.view.NepalView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NepalPresenter {

    NepalView view;
    ApiNepalServiceInteractor mApiNepalServiceInteractor;
    String TAG="Error Message";

    @Inject
    public NepalPresenter(ApiNepalServiceInteractor mApiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = mApiNepalServiceInteractor;
    }

    public void bind(NepalView view) {
        this.view = view;
    }

    public void unbind() {
        view = null;
    }


    /**
     * Methods
     ***/

    @SuppressLint("CheckResult")
    public void networkCall() {

        mApiNepalServiceInteractor.getTopCitiesInNepal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse>() {

                    @Override
                    public void accept(ApiResponse apiResponse) throws Exception {

                        if (view != null) {
                            try {
                                view.updateUi(apiResponse);
                            } catch (Exception e) {
                                Log.i(TAG , e.getMessage());
                            }
                        }
                        Log.d("Success Message", " Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG , throwable.getMessage());
                    }
                });

    }

    @SuppressLint("CheckResult")
    public void campingSpotCall(String query, String key) {

        mApiNepalServiceInteractor.getCampingSpots(query, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse>() {

                    @Override
                    public void accept(ApiResponse apiResponse) throws Exception {

                        if (view != null) {
                            try {
                                view.updateUi(apiResponse);
                            } catch (Exception e) {
                                Log.i(TAG , e.getMessage());
                            }
                        }
                        Log.d("Success Message", " Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG , throwable.getMessage());
                    }
                });

    }

    @SuppressLint("CheckResult")
    public void restaurantCall(String query, String key) {

        mApiNepalServiceInteractor.getRestaurants(query, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse>() {

                    @Override
                    public void accept(ApiResponse apiResponse) throws Exception {

                        if (view != null) {
                            try {
                                view.updateUi(apiResponse);
                            } catch (Exception e) {
                                Log.i(TAG , e.getMessage());
                            }
                        }
                        Log.d("Success Message", " Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG , throwable.getMessage());
                    }
                });

    }

    @SuppressLint("CheckResult")
    public void cashMachineCall(String query, String key) {

        mApiNepalServiceInteractor.getRestaurants(query, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiResponse>() {

                    @Override
                    public void accept(ApiResponse apiResponse) throws Exception {

                        if (view != null) {
                            try {
                                view.updateUi(apiResponse);
                            } catch (Exception e) {
                                Log.i(TAG , e.getMessage());
                            }
                        }
                        Log.d("Success Message", " Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG , throwable.getMessage());
                    }
                });

    }


}
