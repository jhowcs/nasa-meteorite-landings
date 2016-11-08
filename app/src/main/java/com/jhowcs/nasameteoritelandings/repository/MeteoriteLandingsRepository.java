package com.jhowcs.nasameteoritelandings.repository;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.jhowcs.nasameteoritelandings.data.DBContract;
import com.jhowcs.nasameteoritelandings.data.DBHelper;
import com.jhowcs.nasameteoritelandings.model.Geolocation;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;

import java.util.Arrays;

import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_FALL;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_ID;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_LATITUDE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_LONGITUDE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_MASS;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_NAME;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_NAMETYPE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_RECCLASS;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_RECLAT;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_RECLONG;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_TYPE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_YEAR;

/**
 * Created by jonathan_campos on 03/11/2016.
 */

public class MeteoriteLandingsRepository {

    private DBHelper mDbHelper;
    private ContentResolver mContentResolver;

    public MeteoriteLandingsRepository(Context context) {
        mDbHelper = new DBHelper(context);
        mContentResolver = context.getContentResolver();
    }

    public long insert(NasaMeteoriteLandings model) {
        ContentValues cv = getContentValuesFromMeteoriteLandingObject(model);
        //
        Uri uri = mContentResolver.insert(DBContract.MeteorLandEntry.CONTENT_URI, cv);

        return Long.parseLong(uri.getLastPathSegment());
    }

    @NonNull
    private ContentValues getContentValuesFromMeteoriteLandingObject(NasaMeteoriteLandings model) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, model.getId());
        cv.put(COLUMN_FALL, model.getFall());
        cv.put(COLUMN_TYPE, model.getGeolocation().getType());
        cv.put(COLUMN_LATITUDE, model.getGeolocation().getCoordinates().get(1));
        cv.put(COLUMN_LONGITUDE, model.getGeolocation().getCoordinates().get(0));
        cv.put(COLUMN_MASS, model.getMass());
        cv.put(COLUMN_NAME, model.getName());
        cv.put(COLUMN_NAMETYPE, model.getNametype());
        cv.put(COLUMN_RECCLASS, model.getRecclass());
        cv.put(COLUMN_RECLAT, model.getReclat());
        cv.put(COLUMN_RECLONG, model.getReclong());
        cv.put(COLUMN_YEAR, model.getYear());
        return cv;
    }

    public static NasaMeteoriteLandings nasaMeteoriteLandingsFromCursor(Cursor cursor) {
        NasaMeteoriteLandings obj = new NasaMeteoriteLandings();
        Geolocation geoObj = new Geolocation();

        obj.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
        obj.setFall(cursor.getString(cursor.getColumnIndex(COLUMN_FALL)));

        geoObj.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
        geoObj.setCoordinates(Arrays.asList(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUDE)),
                                            cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUDE))));

        obj.setGeolocation(geoObj);
        obj.setMass(cursor.getDouble(cursor.getColumnIndex(COLUMN_MASS)));
        obj.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        obj.setNametype(cursor.getString(cursor.getColumnIndex(COLUMN_NAMETYPE)));
        obj.setRecclass(cursor.getString(cursor.getColumnIndex(COLUMN_RECCLASS)));
        obj.setReclat(cursor.getDouble(cursor.getColumnIndex(COLUMN_RECLAT)));
        obj.setReclong(cursor.getDouble(cursor.getColumnIndex(COLUMN_RECLONG)));
        obj.setYear(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)));

        return obj;
    }
}