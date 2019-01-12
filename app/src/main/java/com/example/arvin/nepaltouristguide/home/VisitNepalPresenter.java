package com.example.arvin.nepaltouristguide.home;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;
import com.example.arvin.nepaltouristguide.home.VisitNepalView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class VisitNepalPresenter extends BasePresenter<VisitNepalView> {

    VisitNepalView view;
    String TAG = "Error Message";

    @Inject
    public VisitNepalPresenter(ApiNepalServiceInteractor mApiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = mApiNepalServiceInteractor;
    }

    /**
     * Methods
     ***/

    @SuppressLint("CheckResult")
    public void networkCall() {

        getApiNepalServiceInteractor().getTopCitiesInNepal()
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
