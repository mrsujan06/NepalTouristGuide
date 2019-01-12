package com.example.arvin.nepaltouristguide.cashMachine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arvin.nepaltouristguide.model.api.ApiList.API_KEY;

public class CashMachineActivity extends BaseActivity implements CashMachineView {

    @BindView(R.id.cashmachineRV)
    RecyclerView mRecyclerView;

    @Inject
    CashMachinePresenter mCashMachinePresenter;

    private CashMachineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_machine);

        ((App) getApplication()).getAppComponent().inject(this);
        mCashMachinePresenter.bind(this);

        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CashMachineActivity.this));

        String place_name = (String) getIntent().getExtras().getSerializable("cityname");
        mCashMachinePresenter.listAllCashMachine(place_name, API_KEY);
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        mAdapter = new CashMachineAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        hideLoading();

    }

    @Override
    protected void onDestroy() {
        mCashMachinePresenter.unbind();
        super.onDestroy();
    }
}
