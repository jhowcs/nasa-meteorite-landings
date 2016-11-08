package com.jhowcs.nasameteoritelandings.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.jhowcs.nasameteoritelandings.data.DBContract.DATABASE_NAME;
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
import static com.jhowcs.nasameteoritelandings.data.DBContract.MeteorLandEntry.TABLE_NAME;

/**
 * Created by jonathan_campos on 03/11/2016.
 *
 * Class that represents the Database
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbCreate = new StringBuilder();

        sbCreate.append("CREATE TABLE ").append(TABLE_NAME).append(" ( ");
        sbCreate.append(COLUMN_ID).append(" INTEGER PRIMARY KEY");
        sbCreate.append(", ").append(COLUMN_FALL).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_TYPE).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_LATITUDE).append(" NUMERIC NOT NULL");
        sbCreate.append(", ").append(COLUMN_LONGITUDE).append(" NUMERIC NOT NULL");
        sbCreate.append(", ").append(COLUMN_MASS).append(" NUMERIC NOT NULL");
        sbCreate.append(", ").append(COLUMN_NAME).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_NAMETYPE).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_RECCLASS).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_RECLAT).append(" NUMERIC NOT NULL");
        sbCreate.append(", ").append(COLUMN_RECLONG).append(" NUMERIC NOT NULL");
        sbCreate.append(", ").append(COLUMN_YEAR).append(" TEXT NOT NULL");
        sbCreate.append(" ) ");

        sqLiteDatabase.execSQL(sbCreate.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }
}
