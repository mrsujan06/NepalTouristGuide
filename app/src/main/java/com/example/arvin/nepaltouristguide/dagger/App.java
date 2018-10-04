package com.example.arvin.nepaltouristguide.dagger;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    AppComponent mAppComponent;
    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }





}
