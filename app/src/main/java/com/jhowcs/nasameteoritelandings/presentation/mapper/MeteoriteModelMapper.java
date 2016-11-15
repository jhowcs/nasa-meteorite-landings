package com.jhowcs.nasameteoritelandings.presentation.mapper;

import android.database.Cursor;

import com.jhowcs.nasameteoritelandings.data.entity.Geolocation;
import com.jhowcs.nasameteoritelandings.presentation.model.MeteoriteModel;

import java.util.Arrays;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_LATITUDE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_LONGITUDE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_MASS;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_NAME;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_RECCLASS;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_RECLAT;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_RECLONG;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_TYPE;
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.COLUMN_YEAR;

/**
 * Created by jonathan on 13/11/16.
 */

//TODO modify 'cause we can't access static member from data
public class MeteoriteModelMapper {

    public static MeteoriteModel meteoriteFromCursor(Cursor cursor) {
        MeteoriteModel obj = new MeteoriteModel();
        Geolocation geoObj = new Geolocation();

        geoObj.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
        geoObj.setCoordinates(Arrays.asList(cursor.getDouble(cursor.getColumnIndex(COLUMN_LATITUDE)),
                cursor.getDouble(cursor.getColumnIndex(COLUMN_LONGITUDE))));

        obj.setMass(cursor.getDouble(cursor.getColumnIndex(COLUMN_MASS)));
        obj.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
        obj.setRecclass(cursor.getString(cursor.getColumnIndex(COLUMN_RECCLASS)));
        obj.setReclat(cursor.getDouble(cursor.getColumnIndex(COLUMN_RECLAT)));
        obj.setReclong(cursor.getDouble(cursor.getColumnIndex(COLUMN_RECLONG)));
        obj.setYear(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)));

        return obj;
    }
}
