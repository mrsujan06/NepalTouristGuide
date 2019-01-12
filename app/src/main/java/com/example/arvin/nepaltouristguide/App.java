package com.example.arvin.nepaltouristguide;

import android.app.Application;
import android.content.Context;

import com.example.arvin.nepaltouristguide.dagger.AppComponent;
import com.example.arvin.nepaltouristguide.dagger.DaggerAppComponent;

public class App extends Application {

    private static App sInstance;
    private AppComponent mAppComponent;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.create();
        mContext = getApplicationContext();
    }

    public static App getInstance() {
        if (sInstance == null) {
            sInstance = new App();
        }
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public Context getContext() {
        return mContext;
    }

}
