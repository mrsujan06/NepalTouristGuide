package com.example.arvin.nepaltouristguide.dagger;

import com.example.arvin.nepaltouristguide.camping.CampingActivity;
import com.example.arvin.nepaltouristguide.home.VisitNepalActivity;
import com.example.arvin.nepaltouristguide.cashMachine.CashMachineActivity;
import com.example.arvin.nepaltouristguide.mountain.Mountain;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;

import dagger.Component;

@Component(modules = {NepalGuideModule.class})
public interface AppComponent {
    void inject(VisitNepalActivity activity);

    void inject(CampingActivity campingActivity);

    void inject(RestaurantActivity restaurantActivity);

    void inject(CashMachineActivity cashMachineActivity);

    void inject(Mountain mountain);

}
