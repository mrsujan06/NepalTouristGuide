package com.example.arvin.nepaltouristguide.mountain;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;

public class Mountain extends BaseActivity implements MountainView {

    @BindView(R.id.mountainRV)
    RecyclerView mRecyclerView;
    @Inject
    MountainPresenter mMountainPresenter;
    MountainAdapter mAdapter;
    private String nepalMountains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        nepalMountains = getResources().getString(R.string.mountains);

        ((App) getApplication()).getAppComponent().inject(this);

        mMountainPresenter.bind(this);

        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(Mountain.this));
        mMountainPresenter.listOfMountain(nepalMountains, API_KEY);
    }


    @Override
    protected void onDestroy() {
        mMountainPresenter.unbind();
        super.onDestroy();
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        mAdapter = new MountainAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {

        Toast.makeText(this, "Error on MountainActivity", Toast.LENGTH_SHORT).show();
        hideLoading();
    }
}
