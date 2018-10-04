package com.example.arvin.nepaltouristguide.dagger;

import android.content.Context;

import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiServiceInteractorImp;

import dagger.Module;
import dagger.Provides;

@Module
public class NepalGuideModule {

    @Provides
    public ApiNepalServiceInteractor getApiNepalServiceInteractor() {
        return new ApiServiceInteractorImp();
    }

}
