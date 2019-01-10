package com.example.arvin.nepaltouristguide.home;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.utils.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitNepalActivity extends BaseActivity implements VisitNepalView {


    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    @Inject
    VisitNepalPresenter mVisitNepalPresenter;
    private VisitNepalAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        mVisitNepalPresenter.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(VisitNepalActivity.this));

        mVisitNepalPresenter.networkCall();
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        mAdapter = new VisitNepalAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
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
        mVisitNepalPresenter.unbind();
    }

}
