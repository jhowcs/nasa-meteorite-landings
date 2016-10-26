package com.jhowcs.nasameteoritelandings.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.adapter.NasaMeteoriteAdapter;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.presenter.NasaMeteoritePresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView rvMeteorites;
    private RelativeLayout rlProgress;
    private RelativeLayout rlRetry;
    private ImageView imgRetry;

    private NasaMeteoriteAdapter mAdapter;
    private NasaMeteoritePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMeteorites = (RecyclerView) findViewById(R.id.rv_meteorites);
        rlProgress   = (RelativeLayout) findViewById(R.id.rl_progress);
        rlRetry      = (RelativeLayout) findViewById(R.id.rl_retry);

        mPresenter = new NasaMeteoritePresenter(this);
        mAdapter = new NasaMeteoriteAdapter();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        rvMeteorites.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMeteorites.setItemAnimator(new DefaultItemAnimator());
        rvMeteorites.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.loadMeteoriteList();
    }

    @Override
    public void renderMeteoriteList(List<NasaMeteoriteLandings> nasaMeteoriteLandings) {
        mAdapter.setListMeteoriteLandings(nasaMeteoriteLandings);
    }

    @Override
    public void onShowProgress() {
        rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        rlProgress.setVisibility(View.GONE);
    }

    @Override
    public void onShowRetry() {
        rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideRetry() {
        rlRetry.setVisibility(View.GONE);
    }
}
