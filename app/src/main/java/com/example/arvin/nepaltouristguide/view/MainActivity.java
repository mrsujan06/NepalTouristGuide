package com.example.arvin.nepaltouristguide.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.adapter.NepalAdapter;
import com.example.arvin.nepaltouristguide.dagger.App;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Result;
import com.example.arvin.nepaltouristguide.presenter.NepalPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NepalView, SearchView.OnQueryTextListener {

    NepalPresenter mNepalPresenter;
    RecyclerView mRecyclerView;
    NepalAdapter mAdapter;
    Toolbar mToolbar;
    List<ApiResponse> mResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getAppComponent().inject(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        mAdapter = new NepalAdapter(response,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchbar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
     return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
      //  filter(newText.toString());


        Toast.makeText(this, "Searching bro... ", Toast.LENGTH_SHORT).show();
        return true;
    }


}
