package com.example.arvin.nepaltouristguide.model.Interactor;

import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.api.ApiNepalService;

import io.reactivex.Observable;

public interface ApiNepalServiceInteractor {
    Observable<ApiResponse> getTopCitiesInNepal();
    Observable<ApiResponse> getCampingSpots(String query , String key);
    Observable<ApiResponse> getRestaurants(String query , String key);
    Observable<ApiResponse> getCashMachine(String query , String key);
}
