package com.jhowcs.nasameteoritelandings.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import static com.jhowcs.nasameteoritelandings.data.DBContract.PATH_METEORLAND;

/**
 * Created by jonathan_campos on 03/11/2016.
 */

public class MeteorLandProvider extends ContentProvider {

    private static final UriMatcher mUriMatcher = buildUriMatcher();

    private static final int METEORITE_TABLE = 0; // all rows
    private static final int METEORITE_TABLE_ROW = 1; // specific row

    private DBHelper mDbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DBContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, PATH_METEORLAND, METEORITE_TABLE);
        matcher.addURI(authority, PATH_METEORLAND + "/#", METEORITE_TABLE_ROW);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        sortOrder = sortOrder == null ? DBContract.MeteorLandEntry.COLUMN_MASS : sortOrder;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (mUriMatcher.match(uri)) {
            case METEORITE_TABLE:
                Cursor cursor = db.query(DBContract.MeteorLandEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            case METEORITE_TABLE_ROW:
                selection = "id = " + uri.getLastPathSegment();
                cursor = db.query(DBContract.MeteorLandEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch (mUriMatcher.match(uri)) {
            case METEORITE_TABLE:
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                long rowId = db.insert(DBContract.MeteorLandEntry.TABLE_NAME, null, contentValues);
                Uri newUri = ContentUris.withAppendedId(uri, rowId);
                db.close();
                notifyChanges(uri);
                return newUri;
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    private void notifyChanges(Uri uri) {
        if(getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }
}
