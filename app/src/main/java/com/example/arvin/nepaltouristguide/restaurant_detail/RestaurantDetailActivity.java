package com.example.arvin.nepaltouristguide.restaurant_detail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.home.VisitNepalActivity;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.restaurant.RestaurantActivity;
import com.example.arvin.nepaltouristguide.service.api.ApiList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailView {


    @BindView(R.id.restaurant_picture)
    ImageView restaurantPhoto;
    @BindView(R.id.restaurantDetailToolbar)
    Toolbar mToolbar;
    @BindView(R.id.restaurant_name)
    TextView restaurantName;
    @BindView(R.id.phone_number_tv)
    TextView phonenumber;
    @BindView(R.id.address_tv)
    TextView address;
    @BindView(R.id.open_close_tv)
    TextView status;
    @BindView(R.id.openingTimes)
    TextView openingTimes;
    @Inject
    RestaurantDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(this);
        mPresenter.bind(this);
        ButterKnife.bind(this);

        String restaurant_photo = (String) getIntent().getExtras().getSerializable(VisitNepalActivity.PLACE_PHOTO);
        String restaurant_name = (String) getIntent().getExtras().getSerializable(RestaurantActivity.RESTAURANT_NAME);
        String restaurant_id = (String) getIntent().getExtras().getSerializable(RestaurantActivity.RESTAURANT_ID);

        Glide.with(this).asBitmap().load(ApiList.IMAGE_URL + restaurant_photo + "&key=" + ApiList.API_KEY).into(restaurantPhoto);
        mToolbar.setTitle(restaurant_name);

        mPresenter.getRestaurantDetailPresenter(restaurant_id, ApiList.API_KEY);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_restaurant_detail;
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(PlaceDetailResponse response) {
        address.setText(response.getResult().getFormattedAddress());
        phonenumber.setText(response.getResult().getInternationalPhoneNumber());

        if (response.getResult().getOpeningHours().getOpenNow()) {
            status.setText(R.string.open);
            status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            status.setText(R.string.closed);
            status.setTextColor(getResources().getColor(R.color.red));
        }

        List<String> weekDayText = new ArrayList<>();
        weekDayText.addAll(response.getResult().getOpeningHours().getWeekdayText());

        StringBuilder builder = new StringBuilder();
        for (String items : weekDayText) {
            builder.append(items + "\n");
        }
        openingTimes.setText(builder.toString());


        Toast.makeText(this, response.getResult().getOpeningHours().getOpenNow().toString(), Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on VisitNepalActivity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
        mPresenter.dispose();
    }
}
