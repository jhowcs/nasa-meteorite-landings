package com.jhowcs.nasameteoritelandings.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.presenter.NasaMeteoritePresenter;
import com.jhowcs.nasameteoritelandings.service.MeteoriteLandingService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView rvMeteorites;
    private RelativeLayout rlProgress;
    private RelativeLayout rlRetry;
    private ImageView imgRetry;

    private NasaMeteoritePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMeteorites = (RecyclerView) findViewById(R.id.rv_meteorites);
        rlProgress   = (RelativeLayout) findViewById(R.id.rl_progress);
        rlRetry      = (RelativeLayout) findViewById(R.id.rl_retry);

        presenter = new NasaMeteoritePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.loadMeteoriteList();
    }

    @Override
    public void renderMeteoriteList(List<NasaMeteoriteLandings> nasaMeteoriteLandings) {
        for (NasaMeteoriteLandings value : nasaMeteoriteLandings) {
            Log.d("TAG", value.getName());
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
}
