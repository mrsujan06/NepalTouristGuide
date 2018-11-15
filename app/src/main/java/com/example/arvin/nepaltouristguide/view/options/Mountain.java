package com.example.arvin.nepaltouristguide.view.options;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.adapter.MountainAdapter;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.presenter.NepalPresenter;
import com.example.arvin.nepaltouristguide.view.NepalView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Mountain extends AppCompatActivity implements NepalView {


    @BindView(R.id.mountainRV)
    RecyclerView mRecyclerView;

    NepalPresenter mNepalPresenter;
    MountainAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        ((App) getApplication()).getAppComponent().inject(this);

        mNepalPresenter.bind(this);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(Mountain.this));
        mNepalPresenter.campingSpotCall("Mountains in nepal ", "AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc");



    }

    @Inject
    public void getPresenter(NepalPresenter presenter) {
        this.mNepalPresenter = presenter;

    }


    @Override
    public void updateUi(ApiResponse response) {

        mAdapter = new MountainAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
