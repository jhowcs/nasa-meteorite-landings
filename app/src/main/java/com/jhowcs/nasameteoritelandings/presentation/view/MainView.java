package com.jhowcs.nasameteoritelandings.presentation.view;

import android.database.Cursor;

import com.jhowcs.nasameteoritelandings.presentation.model.MeteoriteModel;

import java.util.List;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public interface MainView extends BaseViewWithProgress {

    void renderMeteoriteList(Cursor meteoriteCursor);
}
