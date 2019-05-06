package com.example.arvin.nepaltouristguide.service.Interactor;

import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;

import io.reactivex.Observable;

public interface ApiNepalServiceInteractor {

    Observable<ApiResponse> getTopCitiesInNepal();

    Observable<ApiResponse> getData(String query, String key);

    Observable<PlaceDetailResponse> getDetailData(String placeId, String key);
}
