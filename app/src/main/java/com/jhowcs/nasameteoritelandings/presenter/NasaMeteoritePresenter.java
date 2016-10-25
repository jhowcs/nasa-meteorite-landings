package com.jhowcs.nasameteoritelandings.presenter;

import com.jhowcs.nasameteoritelandings.BuildConfig;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.service.BaseAPI;
import com.jhowcs.nasameteoritelandings.service.MeteoriteLandingService;
import com.jhowcs.nasameteoritelandings.util.DateUtil;
import com.jhowcs.nasameteoritelandings.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public class NasaMeteoritePresenter {

    private MainView mMainView;

    private final int mYear = 2011;

    public NasaMeteoritePresenter(MainView mMainView) {
        this.mMainView = mMainView;
    }

    public void loadMeteoriteList() {
        mMainView.onShowProgress();

        Retrofit retrofit = BaseAPI.getRetrofitInstance();

        MeteoriteLandingService meteoriteLandingService = retrofit.create(MeteoriteLandingService.class);

        String dateQuery = DateUtil.getCompleteStringDate(mYear);

        Call<List<NasaMeteoriteLandings>> call = meteoriteLandingService.getNasaMeteoriteLandings(dateQuery, BuildConfig.NASA_API);

        call.enqueue(new Callback<List<NasaMeteoriteLandings>>() {
            @Override
            public void onResponse(Call<List<NasaMeteoriteLandings>> call, Response<List<NasaMeteoriteLandings>> response) {
                List<NasaMeteoriteLandings> landingsList = response.body();

                mMainView.renderMeteoriteList(landingsList);

                mMainView.onHideProgress();
            }

            @Override
            public void onFailure(Call<List<NasaMeteoriteLandings>> call, Throwable t) {
                mMainView.onHideProgress();
                mMainView.onShowRetry();


            }
        });
    }
}
