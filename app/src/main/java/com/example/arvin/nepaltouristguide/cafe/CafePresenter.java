package com.example.arvin.nepaltouristguide.cafe;

import com.example.arvin.nepaltouristguide.Constants;
import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.base.MvpPresenter;
import com.example.arvin.nepaltouristguide.service.Interactor.ApiNepalServiceInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CafePresenter extends BasePresenter<CafeView> implements MvpPresenter {

    private CompositeDisposable bag = new CompositeDisposable();

    @Inject
    public CafePresenter(ApiNepalServiceInteractor apiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = apiNepalServiceInteractor;
    }

    @Override
    public void getData(String query, String key) {

        query = Constants.CAFES + query.toUpperCase();
        bag.addAll(getApiNepalServiceInteractor().getData(query, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiResponse -> {

                    if (getMvpView() != null) {
                        getMvpView().onFetchDataSuccess(apiResponse);
                    }
                }, throwable -> getMvpView().onFetchDataError(throwable.getMessage()))
        );
        getMvpView().onFetchDataProgress();

    }

    @Override
    public void dispose() {
        bag.clear();
    }
}
