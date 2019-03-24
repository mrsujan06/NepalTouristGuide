package com.example.arvin.nepaltouristguide.model.api;

import com.example.arvin.nepaltouristguide.model.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNepalService {

    @GET("/maps/api/place/textsearch/json?query=Top+cities+in+Nepal&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc")
    Observable<ApiResponse> getTopCitiesInNepal();

    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getCampingSpots(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getRestaurants(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getCashMachine(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?query=Mountain+in+Nepal&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc")
    Observable<ApiResponse> getMountains();


}
