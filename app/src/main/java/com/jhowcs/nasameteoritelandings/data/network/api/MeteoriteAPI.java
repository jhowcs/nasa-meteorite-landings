package com.jhowcs.nasameteoritelandings.data.network.api;

import com.jhowcs.nasameteoritelandings.BuildConfig;
import com.jhowcs.nasameteoritelandings.data.entity.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.data.network.service.MeteoriteService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jonathan on 14/11/16.
 */

public class MeteoriteAPI {

    public List<NasaMeteoriteLandings> getNasaMeteoriteLandings() {
        Retrofit retrofit = BaseAPI.getRetrofitInstance();
        MeteoriteService meteoriteService = retrofit.create(MeteoriteService.class);

        Call<List<NasaMeteoriteLandings>> call = meteoriteService.getNasaMeteoriteLandings("", BuildConfig.NASA_API);

        Response<List<NasaMeteoriteLandings>> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.body();
    }
}
