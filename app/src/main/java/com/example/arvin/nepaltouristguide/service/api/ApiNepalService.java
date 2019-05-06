package com.example.arvin.nepaltouristguide.service.api;

import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.model.placeResponse.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNepalService {


    @GET("/maps/api/place/textsearch/json?")
    Observable<ApiResponse> getData(@Query("query") String query, @Query("key") String key);

    @GET("/maps/api/place/details/json?")
    Observable<PlaceDetailResponse> getDetailData(@Query("placeid") String query, @Query("key") String key);

    @GET("/maps/api/place/textsearch/json?query=Top+cities+in+Nepal&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc")
    Observable<ApiResponse> getTopCitiesInNepal();

    @GET("/maps/api/place/textsearch/json?query=Mountain+in+Nepal&language=en&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc")
    Observable<ApiResponse> getMountains();


}
