package com.jhowcs.nasameteoritelandings.view;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.adapter.NasaMeteoriteAdapter;
import com.jhowcs.nasameteoritelandings.data.DBContract;
import com.jhowcs.nasameteoritelandings.datasync.SyncAdapter;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.presenter.NasaMeteoritePresenter;
import com.jhowcs.nasameteoritelandings.util.ClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        MainView,
        View.OnClickListener,
        ClickListener<NasaMeteoriteLandings>{

    private static final String ADAPTER_SAVED = "ADAPTER_SAVED";
    private static final int LOADER_ID = 1;

    private RecyclerView rvMeteorites;
    private RelativeLayout rlProgress;
    private RelativeLayout rlRetry;
    private ImageView imgRetry;

    private NasaMeteoriteAdapter mAdapter;
    private NasaMeteoritePresenter mPresenter;

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMeteorites = (RecyclerView) findViewById(R.id.rv_meteorites);
        rlProgress = (RelativeLayout) findViewById(R.id.rl_progress);
        rlRetry = (RelativeLayout) findViewById(R.id.rl_retry);
        imgRetry = (ImageView) findViewById(R.id.imgRetry);

        mPresenter = new NasaMeteoritePresenter(this);

        if(savedInstanceState == null) {
            mAdapter = new NasaMeteoriteAdapter();
            //mPresenter.loadMeteoriteList();
            SyncAdapter.syncImmediately(this);
        } else {
            mAdapter = savedInstanceState.getParcelable(ADAPTER_SAVED);
        }

        getLoaderManager().initLoader(LOADER_ID , null , this);
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
    public void renderMeteoriteList(List<NasaMeteoriteLandings> nasaMeteoriteLandings) {
        if(nasaMeteoriteLandings != null) {
            //mAdapter.setListMeteoriteLandings(nasaMeteoriteLandings);
            mAdapter.setClickListener(this);
        }
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
            mPresenter.loadMeteoriteList();
        }
    }

    @Override
    public void onItemClicked(NasaMeteoriteLandings meteoriteLandingsObj) {
        Intent intent = new Intent(this, NasaMeteoriteDetail.class);

        intent.putExtra(NasaMeteoriteDetail.NASA_METEORITE_PARCELABLE, meteoriteLandingsObj);

        startActivity(intent);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        onShowProgress();
        return new CursorLoader(this, DBContract.MeteorLandEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished");
        onHideProgress();
        mAdapter.setCursor(cursor);
        mAdapter.setClickListener(this);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.setCursor(null);
    }
}
