package com.example.arvin.nepaltouristguide.service.Interactor;

import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;

import io.reactivex.Observable;

public interface ApiNepalServiceInteractor {

    Observable<ApiResponse> getTopCitiesInNepal();

    Observable<ApiResponse> getCampingSpots(String query, String key);

    Observable<ApiResponse> getRestaurants(String query, String key);

    Observable<ApiResponse> getCashMachine(String query, String key);

    Observable<ApiResponse> getMountains(String query, String key);

    Observable<PlaceDetailResponse> getRestaurantDetail(String placeId, String key);

}
