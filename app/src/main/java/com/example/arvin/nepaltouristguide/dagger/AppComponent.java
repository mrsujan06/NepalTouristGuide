package com.example.arvin.nepaltouristguide.dagger;

import com.example.arvin.nepaltouristguide.view.MainActivity;
import com.example.arvin.nepaltouristguide.view.options.CampingSpot;
import com.example.arvin.nepaltouristguide.view.options.CashMachine;
import com.example.arvin.nepaltouristguide.view.options.Restaurant;

import dagger.Component;

@Component(modules = {NepalGuideModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(CampingSpot campingSpot);
    void inject(Restaurant restaurant);
    void inject(CashMachine cashMachine);

}
