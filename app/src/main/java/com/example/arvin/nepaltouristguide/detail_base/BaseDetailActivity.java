package com.example.arvin.nepaltouristguide.detail_base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.arvin.nepaltouristguide.Constants;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;
import com.example.arvin.nepaltouristguide.restaurant_detail.RestaurantDetailCommentAdapter;
import com.taufiqrahman.reviewratings.RatingReviews;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class BaseDetailActivity extends BaseActivity implements DetailMvpView {

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
    @BindView(R.id.rating_tv)
    TextView rating;
    @BindView(R.id.userRatingTotal_tv)
    TextView userRatingTotalTv;
    @BindView(R.id.ratingBar)
    RatingBar mRatingBar;
    @BindView(R.id.rating_reviews_chart)
    RatingReviews ratingReviews;
    @BindView(R.id.user_comment_rv)
    RecyclerView userCommentrv;

    RestaurantDetailCommentAdapter mAdapter;

    private String placePhoneNumber;
    private String placeLatitude;
    private String placeLongitude;
    private String placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    public abstract void getPlaceDetails(String restaurant_id);

    @OnClick(R.id.direction_button)
    public void onDirectionButtonClicked() {
        String label = placeName;
        String uriBegin = "geo:" + placeLatitude + "," + placeLongitude;
        String query = placeLatitude + "," + placeLongitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery;
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.locationOnMapButton)
    public void onMapButtonClicked() {
        Toast.makeText(this, "Map Button clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.callButton)
    public void onCallButtonClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + placePhoneNumber));
        startActivity(intent);
        Toast.makeText(this, "Call Button clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.favouriteButton)
    public void onFavouriteButtonClicked() {
        Toast.makeText(this, "Favourite Button clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_place_details;
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(PlaceDetailResponse response) {
        showLoading();
        Glide.with(this).asBitmap().load(Constants.IMAGE_URL + response.getResult().getPhotos().get(0).getPhotoReference() + "&key=" + Constants.API_KEY).into(restaurantPhoto);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        Toast.makeText(this, "Error on RestaurantNepalActivity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void setToolbarName(String placeName) {
        this.placeName = placeName;
        mToolbar.setTitle(placeName);
    }

    @Override
    public void setPlaceName(String placeName) {
        restaurantName.setText(placeName);
    }

    @Override
    public void setPlaceAddress(String placeAddress) {
        address.setText(placeAddress);
    }

    @Override
    public void setPlacePhoneNumber(String phoneNumber) {
        placePhoneNumber = phoneNumber;
        phonenumber.setText(phoneNumber);
    }

    @Override
    public void setPlaceOpeningStatus(Boolean isOpen) {
        if (isOpen) {
            status.setText(R.string.open);
            status.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            status.setText(R.string.closed);
            status.setTextColor(getResources().getColor(R.color.red));
        }
    }

    @Override
    public void setOpeningTimes(String weekDayText) {
        openingTimes.setText(weekDayText);
    }

    @Override
    public void setRating(String rating) {
        this.rating.setText(rating);
    }

    @Override
    public void setRatingBar(int rating) {
        mRatingBar.setRating(rating);
    }

    @Override
    public void setUserRatingTotal(String totalRating) {
        userRatingTotalTv.setText(totalRating);
    }

    @Override
    public void setRatingChart(int maxBar, String[] barType, int[] colors, int[] raters) {
        ratingReviews.createRatingBars(maxBar, barType, colors, raters);
    }

    @Override
    public void setUserComment(PlaceDetailResponse response) {
        userCommentrv.setLayoutManager(new LinearLayoutManager(BaseDetailActivity.this));
        userCommentrv.setNestedScrollingEnabled(false);
        mAdapter = new RestaurantDetailCommentAdapter(response);
        userCommentrv.setAdapter(mAdapter);
    }

    @Override
    public void setLocation(String latitude, String longitude) {
        placeLatitude = latitude;
        placeLongitude = longitude;
    }

}
