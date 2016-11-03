package com.jhowcs.nasameteoritelandings.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jonathan_campos on 03/11/2016.
 *
 * Class that represents the Database
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dbMeteorite";
    private static final int DATABASE_VERSION = 1;

    public static final String METEORITE_TABLE = "meteorite";

    // COLUMNS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FALL = "fall";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_MASS = "mass";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NAMETYPE = "name_type";
    public static final String COLUMN_RECCLASS = "recclass";
    public static final String COLUMN_RECLAT = "reclat";
    public static final String COLUMN_RECLONG = "reclong";
    public static final String COLUMN_YEAR = "year";
    //

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sbCreate = new StringBuilder();

        sbCreate.append("CREATE TABLE ").append(METEORITE_TABLE).append(" ( ");
        sbCreate.append(COLUMN_ID).append(" INTEGER PRIMARY KEY");
        sbCreate.append(", ").append(COLUMN_FALL).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_TYPE).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_LATITUDE).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_LONGITUDE).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_MASS).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_NAME).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_NAMETYPE).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_RECCLASS).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_RECLAT).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_RECLONG).append(" TEXT NOT NULL");
        sbCreate.append(", ").append(COLUMN_YEAR).append(" TEXT NOT NULL");
        sbCreate.append(" ) ");

        sqLiteDatabase.execSQL(sbCreate.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
    }
}
