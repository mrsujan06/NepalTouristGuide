package com.example.arvin.nepaltouristguide.dagger;

import com.example.arvin.nepaltouristguide.cafe.CafeActivity;
import com.example.arvin.nepaltouristguide.cafe_detail.CafeDetailActivity;
import com.example.arvin.nepaltouristguide.camping.CampingActivity;
import com.example.arvin.nepaltouristguide.cash_machine.CashMachineActivity;
import com.example.arvin.nepaltouristguide.home.VisitNepalActivity;
import com.example.arvin.nepaltouristguide.mountain.MountainActivity;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;
import com.example.arvin.nepaltouristguide.restaurant_detail.RestaurantDetailActivity;

import dagger.Component;

@Component(modules = {NepalGuideModule.class})
public interface AppComponent {
    void inject(VisitNepalActivity activity);

    void inject(CampingActivity campingActivity);

    void inject(RestaurantActivity restaurantActivity);

    void inject(CashMachineActivity cashMachineActivity);

    void inject(MountainActivity mountain);

    void inject(RestaurantDetailActivity restaurantDetailActivity);

    void inject(CafeActivity cafeActivity);

    void inject(CafeDetailActivity cafeDetailActivity);

}
