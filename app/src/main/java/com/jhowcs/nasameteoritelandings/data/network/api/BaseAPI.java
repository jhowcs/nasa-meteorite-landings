package com.jhowcs.nasameteoritelandings.data.network.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public class BaseAPI {

    private static Retrofit mRetrofit;

    public static final String BASE_URL = "https://data.nasa.gov/resource/";

    public static Retrofit getRetrofitInstance() {

        if(mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }

        return mRetrofit;
    }

}
