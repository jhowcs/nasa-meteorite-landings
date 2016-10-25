package com.jhowcs.nasameteoritelandings.view;

import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;

import java.util.List;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public interface MainView extends BaseViewWithProgress {

    void renderMeteoriteList(List<NasaMeteoriteLandings> nasaMeteoriteLandings);
}
