package com.example.arvin.nepaltouristguide.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.adapter.NepalAdapter;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiServiceInteractorImp;
import com.example.arvin.nepaltouristguide.presenter.NepalPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NepalView {

    ApiNepalServiceInteractor mApiNepalServiceInteractor;
    NepalPresenter mNepalPresenter;
    RecyclerView mRecyclerView;
    NepalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getAppComponent().inject(this);

        mNepalPresenter.bind(this);


        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mNepalPresenter.networkCall();
    }

    @Inject
    public void getNepalPresenter(NepalPresenter presenter) {
        this.mNepalPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNepalPresenter.unbind();
    }


    @Override
    public void updateUi(ApiResponse response) {
        mAdapter = new NepalAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


}
