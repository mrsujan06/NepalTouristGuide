package com.example.arvin.nepaltouristguide.restaurant_detail;

import com.example.arvin.nepaltouristguide.base.BasePresenter;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.service.Interactor.ApiNepalServiceInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RestaurantDetailPresenter extends BasePresenter<RestaurantDetailView> {

    private CompositeDisposable bag = new CompositeDisposable();

    @Inject
    public RestaurantDetailPresenter(ApiNepalServiceInteractor mApiNepalServiceInteractor) {
        this.mApiNepalServiceInteractor = mApiNepalServiceInteractor;
    }

    public void getRestaurantDetailPresenter(String placeId, String apiKey) {

        bag.add(mApiNepalServiceInteractor.getRestaurantDetail(placeId, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PlaceDetailResponse>() {

                    @Override
                    public void accept(PlaceDetailResponse placeDetailResponse) throws Exception {
                        getMvpView().onFetchDataSuccess(placeDetailResponse);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().onFetchDataError(throwable.getMessage());
                    }
                }));

        getMvpView().onFetchDataProgress();
    }

    public void dispose() {

        if (!bag.isDisposed()) {
            bag.clear();
        }
    }
}
