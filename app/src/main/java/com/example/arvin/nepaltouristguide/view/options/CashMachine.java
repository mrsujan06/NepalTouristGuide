package com.example.arvin.nepaltouristguide.view.options;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.adapter.CashMachineAdapter;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiNepalServiceInteractor;
import com.example.arvin.nepaltouristguide.model.Interactor.ApiServiceInteractorImp;
import com.example.arvin.nepaltouristguide.presenter.NepalPresenter;
import com.example.arvin.nepaltouristguide.view.NepalView;

import javax.inject.Inject;

public class CashMachine extends AppCompatActivity implements NepalView {

    NepalPresenter mNepalPresenter;
    RecyclerView mRecyclerView;
    CashMachineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_machine);

        ((App) getApplication()).getAppComponent().inject(this);
        mNepalPresenter.bind(this);

        mRecyclerView = findViewById(R.id.cashmachineRV);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CashMachine.this));

        String place_name = (String) getIntent().getExtras().getSerializable("cityname");
        mNepalPresenter.cashMachineCall("ATM in " + place_name.toUpperCase(), "AIzaSyBT2bl_XWXG7-fsWtCNyGrTD8wFxaBxbTc");


    }



    @Inject
    public void getNepalPresenter(NepalPresenter presenter) {
        this.mNepalPresenter = presenter;
    }

    @Override
    public void updateUi(ApiResponse response) {

        mAdapter = new CashMachineAdapter(response, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
