package com.jhowcs.nasameteoritelandings.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jonathan_campos on 04/11/2016.
 */

public class DBContract {
    public static final String DATABASE_NAME = "dbMeteorite";
    public static final String CONTENT_AUTHORITY = "com.jhowcs.nasameteoritelandings.datasync.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_METEORLAND = "meteor";

    public static final class MeteorLandEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_METEORLAND).build();

        public static final String TABLE_NAME = "meteorite";

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
    }
}
