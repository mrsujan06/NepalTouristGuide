package com.example.arvin.nepaltouristguide.place_options;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.Constants;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.cafe.CafeActivity;
import com.example.arvin.nepaltouristguide.camping.CampingActivity;
import com.example.arvin.nepaltouristguide.cash_machine.CashMachineActivity;
import com.example.arvin.nepaltouristguide.home.VisitNepalActivity;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;

import java.util.Objects;

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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
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

        Glide.with(this).asBitmap().load(Constants.IMAGE_URL + place_photo + "&key=" + Constants.API_KEY).into(this.placePhoto);
        setTitle(place_name);
    }


    @OnClick(R.id.campingButton)
    public void startCampingActivity(View view) {

        Intent intent = new Intent(view.getContext(), CampingActivity.class);
        intent.putExtra(CITY_NAME, place_name);
        view.getContext().startActivity(intent);

    }

    @OnClick(R.id.cafeButton)
    public void startCafeActivity(View view) {
        Intent intent = new Intent(view.getContext(), CafeActivity.class);
        intent.putExtra(CITY_NAME, place_name);
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
