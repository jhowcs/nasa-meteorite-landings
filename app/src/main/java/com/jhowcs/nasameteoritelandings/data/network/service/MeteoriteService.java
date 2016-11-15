package com.jhowcs.nasameteoritelandings.data.network.service;

import com.jhowcs.nasameteoritelandings.data.entity.NasaMeteoriteLandings;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public interface MeteoriteService {

    /**
     * This comprehensive data set from The Meteoritical Society contains information on all of the
     * known meteorite landings. For more information please read the Documents at
     *
     * @see <a href="https://dev.socrata.com/foundry/data.nasa.gov/y77d-th95">Nasa API</a>
     *
     * @param year Year that the query will start bringing information
     * @return A list with all data found at Nasa's database
     */
    @GET("y77d-th95.json")
    Call<List<NasaMeteoriteLandings>> getNasaMeteoriteLandings(@Query("year") String year, @Header("X-App-Token") String appToken);
}
