package com.example.arvin.nepaltouristguide.mountain;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

import javax.inject.Inject;

import butterknife.BindView;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;

public class MountainActivity extends BaseActivity implements MountainView {

    private static final String MOUNTAINS_IN_NEPAL = "Mountains+in+nepal";

    @BindView(R.id.mountainRV)
    RecyclerView mRecyclerView;

    @Inject
    MountainPresenter mMountainPresenter;

    private MountainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(this);

        mMountainPresenter.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MountainActivity.this));
        getMountainsInNepal();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_mountain;
    }

    private void getMountainsInNepal(){
        mMountainPresenter.listOfMountain(MOUNTAINS_IN_NEPAL, API_KEY);
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

    @Override
    protected void onDestroy() {
        mMountainPresenter.unbind();
        super.onDestroy();
    }

}
