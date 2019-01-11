package com.example.arvin.nepaltouristguide.placeOptions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.camping.CampingActivity;
import com.example.arvin.nepaltouristguide.cashMachine.CashMachineActivity;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;
import com.example.arvin.nepaltouristguide.mountain.Mountain;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;


public class PlacesOptions extends BaseActivity implements PlaceOptionView {

    @BindView(R.id.placePhoto)
    ImageView placePhoto;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    String place_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_options);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();

    }

    @Override
    public void getIncomingIntent() {

        if (getIntent().hasExtra("place_photo") && getIntent().hasExtra("place_name"))

        {
            String place_photo = (String) getIntent().getExtras().getSerializable("place_photo");
            place_name = (String) getIntent().getExtras().getSerializable("place_name");
            setImage(place_photo, place_name);
        }

    }


    @Override
    public void setImage(String place_photo, String place_name) {
        Glide.with(this).asBitmap().load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference=" + place_photo + "&key=" + API_KEY
        ).into(this.placePhoto);
        setTitle(place_name);
    }


    @OnClick(R.id.campingButton)
    public void startCampingActivity(View view) {

        Intent intent = new Intent(view.getContext(), CampingActivity.class);
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
        Intent intent = new Intent(view.getContext(), CashMachineActivity.class);
        intent.putExtra("cityname", place_name);
        view.getContext().startActivity(intent);
    }

    @OnClick(R.id.RestaurantButton)
    public void startRestaurantActivity(View view) {
        Intent intent = new Intent(view.getContext(), RestaurantActivity.class);
        intent.putExtra("cityname", place_name);
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
