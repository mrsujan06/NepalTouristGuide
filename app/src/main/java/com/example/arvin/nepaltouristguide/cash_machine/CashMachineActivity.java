package com.example.arvin.nepaltouristguide.cash_machine;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.example.arvin.nepaltouristguide.App;
import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.base.BaseActivity;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.place_options.PlacesOptionsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.arvin.nepaltouristguide.Constants.API_KEY;

public class CashMachineActivity extends BaseActivity implements CashMachineView, OnCashSeletedInterface {

    @BindView(R.id.cashmachineRV)
    RecyclerView mRecyclerView;

    @Inject
    CashMachinePresenter mCashMachinePresenter;

    private CashMachineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getAppComponent().inject(this);
        mCashMachinePresenter.bind(this);

        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CashMachineActivity.this));

        String place_Name = (String) getIntent().getExtras().getSerializable(PlacesOptionsActivity.CITY_NAME);
        mCashMachinePresenter.listAllCashMachine(place_Name, API_KEY);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_cash_machine;
    }

    @Override
    public void onFetchDataProgress() {
        showLoading();
    }

    @Override
    public void onFetchDataSuccess(ApiResponse response) {
        OnCashSeletedInterface mListener = CashMachineActivity.this;
        mAdapter = new CashMachineAdapter(response, mListener);
        mRecyclerView.setAdapter(mAdapter);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
        hideLoading();
    }

    @Override
    public void onCashSelected(int index, ApiResponse apiResponse) {
        Toast.makeText(this, apiResponse.getResults().get(index).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mCashMachinePresenter.unbind();
        mCashMachinePresenter.dispose();
        super.onDestroy();
    }
}
