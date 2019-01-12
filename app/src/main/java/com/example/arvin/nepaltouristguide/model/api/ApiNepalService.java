package com.example.arvin.nepaltouristguide.model.api;

import com.example.arvin.nepaltouristguide.model.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;
import static com.example.arvin.nepaltouristguide.model.api.ApiList.BASE_URL;
import static com.example.arvin.nepaltouristguide.model.api.ApiList.QUERY;
import static com.example.arvin.nepaltouristguide.model.api.ApiList.RELATIVE_URL_CAMPING_SPOT;
import static com.example.arvin.nepaltouristguide.model.api.ApiList.RELATIVE_URL_CASHMACHINE;
import static com.example.arvin.nepaltouristguide.model.api.ApiList.RELATIVE_URL_RESTAURANTS;

public interface ApiNepalService {

    //https://maps.googleapis.com/maps/api/place/textsearch/json?query=bus+stops+in+pokhara&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc
    @GET("/maps/api/place/textsearch/json?query=Top+cities+in+Nepal&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc")
    Observable<ApiResponse> getTopCitiesInNepal();

    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getCampingSpots(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getRestaurants(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getCashMachine(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?query=MountainActivity+in+Nepal&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc")
    Observable<ApiResponse> getMountains();


}