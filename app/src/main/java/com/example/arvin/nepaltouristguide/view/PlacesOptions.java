package com.example.arvin.nepaltouristguide.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.view.options.CampingSpot;
import com.example.arvin.nepaltouristguide.view.options.CashMachine;
import com.example.arvin.nepaltouristguide.view.options.Mountain;
import com.example.arvin.nepaltouristguide.view.options.Restaurant;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlacesOptions extends AppCompatActivity {

    ImageView placePhoto;
    String place_name;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_options);

        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        placePhoto = findViewById(R.id.placePhoto);
        getIncomingIntent();

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("place_photo") && getIntent().hasExtra("place_name"))

        {
            String place_photo = (String) getIntent().getExtras().getSerializable("place_photo");
            place_name = (String) getIntent().getExtras().getSerializable("place_name");
            setImage(place_photo, place_name);

        }

    }

    private void setImage(String place_photo, String place_name) {

        Glide.with(this).asBitmap().load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference=" + place_photo + "&key=AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc").into(this.placePhoto);
        setTitle(place_name);
    }


    @OnClick(R.id.campingButton)
    public void startCampingActivity(View view) {

        Intent intent = new Intent(view.getContext(), CampingSpot.class);
        intent.putExtra("cityname", place_name);
        view.getContext().startActivity(intent);

    }

    @OnClick(R.id.mountainButton)
    public void startMountainActivity(View view) {
        Intent intent = new Intent(view.getContext(), Mountain.class);
        view.getContext().startActivity(intent);
    }

    @OnClick(R.id.cashMachineButton)
    public void startCashMachineActivity(View view) {
        Intent intent = new Intent(view.getContext(), CashMachine.class);
        intent.putExtra("cityname", place_name);
        view.getContext().startActivity(intent);
    }

    @OnClick(R.id.RestaurantButton)
    public void startRestaurantActivity(View view) {
        Intent intent = new Intent(view.getContext(), Restaurant.class);
        intent.putExtra("c_name", place_name);
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
