package com.jhowcs.nasameteoritelandings.presentation.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.data.datasync.SyncAdapter;
import com.jhowcs.nasameteoritelandings.presentation.adapter.MeteoriteAdapter;
import com.jhowcs.nasameteoritelandings.presentation.model.MeteoriteModel;
import com.jhowcs.nasameteoritelandings.presentation.presenter.MeteoritePresenter;
import com.jhowcs.nasameteoritelandings.util.ClickListener;

public class MainActivity extends AppCompatActivity
        implements MainView, View.OnClickListener, ClickListener<MeteoriteModel> {

    private static final String ADAPTER_SAVED = "ADAPTER_SAVED";

    private RecyclerView rvMeteorites;
    private RelativeLayout rlProgress;
    private RelativeLayout rlRetry;
    private ImageView imgRetry;

    private MeteoriteAdapter mAdapter;
    private MeteoritePresenter mPresenter;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMeteorites = (RecyclerView) findViewById(R.id.rv_meteorites);
        rlProgress = (RelativeLayout) findViewById(R.id.rl_progress);
        rlRetry = (RelativeLayout) findViewById(R.id.rl_retry);
        imgRetry = (ImageView) findViewById(R.id.imgRetry);

        mPresenter = new MeteoritePresenter(this, getLoaderManager(), this);

        if(savedInstanceState == null) {
            mAdapter = new MeteoriteAdapter();
            mAdapter.setClickListener(this);
            SyncAdapter.syncImmediately(this);
        } else {
            mAdapter = savedInstanceState.getParcelable(ADAPTER_SAVED);
        }

        setupRecyclerView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(ADAPTER_SAVED, mAdapter);
    }

    private void setupRecyclerView() {
        rvMeteorites.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMeteorites.setItemAnimator(new DefaultItemAnimator());
        rvMeteorites.setAdapter(mAdapter);
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

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imgRetry) {
            mPresenter.retry();
        }
    }

    @Override
    public void onItemClicked(MeteoriteModel meteoriteLandingsObj) {
        Intent intent = new Intent(this, MeteoriteDetailActivity.class);

        intent.putExtra(MeteoriteDetailActivity.NASA_METEORITE_PARCELABLE, meteoriteLandingsObj);

        startActivity(intent);
    }

    @Override
    public void renderMeteoriteList(Cursor meteoriteCursor) {
        mAdapter.setCursor(meteoriteCursor);
    }
}
