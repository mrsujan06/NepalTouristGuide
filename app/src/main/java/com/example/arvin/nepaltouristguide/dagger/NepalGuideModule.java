package com.example.arvin.nepaltouristguide.dagger;

import com.example.arvin.nepaltouristguide.service.Interactor.ApiNepalServiceInteractor;
import com.example.arvin.nepaltouristguide.service.Interactor.ApiServiceInteractorImp;

import dagger.Module;
import dagger.Provides;

@Module
public class NepalGuideModule {

    @Provides
    public ApiNepalServiceInteractor getApiNepalServiceInteractor() {
        return new ApiServiceInteractorImp();
    }

}
