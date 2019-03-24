package com.example.arvin.nepaltouristguide.placeOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.camping.CampingActivity;
import com.example.arvin.nepaltouristguide.cashMachine.CashMachineActivity;
import com.example.arvin.nepaltouristguide.home.VisitNepalActivity;
import com.example.arvin.nepaltouristguide.model.api.ApiList;
import com.example.arvin.nepaltouristguide.mountain.MountainActivity;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlacesOptionsActivity extends AppCompatActivity {

    @BindView(R.id.placePhoto)
    ImageView placePhoto;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    String place_name;

    public static final String CITY_NAME = "city_name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_options);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra(VisitNepalActivity.PLACE_PHOTO) && getIntent().hasExtra(VisitNepalActivity.PLACE_NAME)) {
            String place_photo = (String) getIntent().getExtras().getSerializable(VisitNepalActivity.PLACE_PHOTO);
            place_name = (String) getIntent().getExtras().getSerializable(VisitNepalActivity.PLACE_NAME);
            setImage(place_photo, place_name);
        }

    }

    private void setImage(String place_photo, String place_name) {

        Glide.with(this).asBitmap().load(ApiList.IMAGE_URL + place_photo + "&key=" + ApiList.API_KEY).into(this.placePhoto);
        setTitle(place_name);
    }


    @OnClick(R.id.campingButton)
    public void startCampingActivity(View view) {

        Intent intent = new Intent(view.getContext(), CampingActivity.class);
        intent.putExtra(CITY_NAME, place_name);
        view.getContext().startActivity(intent);

    }

    @OnClick(R.id.mountainButton)
    public void startMountainActivity(View view) {
        Intent intent = new Intent(view.getContext(), MountainActivity.class);
        view.getContext().startActivity(intent);
    }

    @OnClick(R.id.cashMachineButton)
    public void startCashMachineActivity(View view) {
        Intent intent = new Intent(view.getContext(), CashMachineActivity.class);
        intent.putExtra(CITY_NAME, place_name);
        view.getContext().startActivity(intent);
    }

    @OnClick(R.id.RestaurantButton)
    public void startRestaurantActivity(View view) {
        Intent intent = new Intent(view.getContext(), RestaurantActivity.class);
        intent.putExtra(CITY_NAME, place_name);
        view.getContext().startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
