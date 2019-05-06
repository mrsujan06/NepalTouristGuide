package com.example.arvin.nepaltouristguide.cash_machine;

import android.util.Log;

import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.service.Interactor.ApiNepalServiceInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CashMachinePresenter extends BasePresenter<CashMachineView> {

    private static final String TAG = CashMachinePresenter.class.getSimpleName();
    private CompositeDisposable bag = new CompositeDisposable();

    @Inject
    public CashMachinePresenter(ApiNepalServiceInteractor apiNepalServiceInteractor) {
        mApiNepalServiceInteractor = apiNepalServiceInteractor;
    }

    void listAllCashMachine(String query, String key) {

        query = "ATM+in+" + query.toUpperCase();
        bag.addAll(getApiNepalServiceInteractor().getData(query, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {

                    if (getMvpView() != null) {
                        try {
                            getMvpView().onFetchDataSuccess(apiResponse);
                        } catch (Exception e) {
                            Log.i(TAG, e.getMessage());
                        }
                    }
                    Log.d("Success Message", " Success");
                }, throwable -> getMvpView().onFetchDataError(throwable.getMessage())));

        getMvpView().onFetchDataProgress();

    }

    void dispose(){
        bag.clear();
    }
}
