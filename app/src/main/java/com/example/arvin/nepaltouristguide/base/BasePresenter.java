package com.example.arvin.nepaltouristguide.base;

import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;

public class BasePresenter<V extends MvpView> {

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

    public ApiNepalServiceInteractor getApiNepalServiceInteractor() {
        return mApiNepalServiceInteractor;
    }


}
