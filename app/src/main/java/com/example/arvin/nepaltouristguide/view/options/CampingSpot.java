package com.example.arvin.nepaltouristguide.view.options;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.adapter.CampingAdapter;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;;
import com.example.arvin.nepaltouristguide.presenter.NepalPresenter;
import com.example.arvin.nepaltouristguide.view.NepalView;

import javax.inject.Inject;

public class CampingSpot extends AppCompatActivity implements NepalView {

    NepalPresenter mNepalPresenter;
    RecyclerView mRecyclerView;
    CampingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping);


        ((App) getApplicationContext()).getAppComponent().inject(this);
        mNepalPresenter.bind(this);

        mRecyclerView = findViewById(R.id.campingRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CampingSpot.this));

        String place_name = (String) getIntent().getExtras().getSerializable("cityname");
        mNepalPresenter.campingSpotCall("camping in " + place_name.toUpperCase(), "AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc");
    }

    @Inject
    public void getNepalPresenter(NepalPresenter presenter) {
        this.mNepalPresenter = presenter;
    }


    @Override
    public void updateUi(ApiResponse response) {
        mAdapter = new CampingAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


}
