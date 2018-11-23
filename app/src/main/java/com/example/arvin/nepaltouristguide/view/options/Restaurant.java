package com.example.arvin.nepaltouristguide.view.options;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.adapter.RestaurantAdapter;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Result;
import com.example.arvin.nepaltouristguide.presenter.NepalPresenter;
import com.example.arvin.nepaltouristguide.view.NepalView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class Restaurant extends AppCompatActivity implements NepalView {

    @BindView(R.id.restaurantRV)
    RecyclerView mRecyclerView;
    NepalPresenter mNepalPresenter;
    RestaurantAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_activity);

        ((App) getApplication()).getAppComponent().inject(this);
        mNepalPresenter.bind(this);

        mRecyclerView = findViewById(R.id.restaurantRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Restaurant.this));

        String place_name = (String) getIntent().getExtras().getSerializable("c_name");
        mNepalPresenter.restaurantCall("Restaurant in " + place_name, "AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc");


    }

    @Inject
    public void getPresenter(NepalPresenter presenter) {
        this.mNepalPresenter = presenter;
    }

    @Override
    public void updateUi( ApiResponse response) {
        mAdapter = new RestaurantAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
