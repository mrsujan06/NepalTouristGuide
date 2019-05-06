package com.example.arvin.nepaltouristguide.service.Interactor;

import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.service.api.ApiNepalService;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceInteractorImp implements ApiNepalServiceInteractor {

    ApiNepalService mService;

    public ApiServiceInteractorImp() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(ApiNepalService.class);

    }

    @Override
    public Observable<ApiResponse> getTopCitiesInNepal() {
        return mService.getTopCitiesInNepal();
    }

    @Override
    public Observable<ApiResponse> getData(String query, String key) {
        return mService.getData(query, key);
    }

    @Override
    public Observable<PlaceDetailResponse> getDetailData(String placeId, String key) {
        return mService.getDetailData(placeId , key);
    }

}
