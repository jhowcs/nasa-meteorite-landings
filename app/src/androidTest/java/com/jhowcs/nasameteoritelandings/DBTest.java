package com.jhowcs.nasameteoritelandings;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jhowcs.nasameteoritelandings.database.DBHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonathan_campos on 03/11/2016.
 *
 * Class created to test our database.
 */

@RunWith(AndroidJUnit4.class)
public class DBTest {

    private Context mContext;

    @Before
    public void setUp() {
        mContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void deleteDatabase() {
        mContext.deleteDatabase(DBHelper.DATABASE_NAME);
    }

    @Test
    public void createDatabase() {
        final Set<String> tables = new HashSet<>();
        tables.add(DBHelper.METEORITE_TABLE);

        deleteDatabase();

        SQLiteDatabase db = new DBHelper(mContext).getWritableDatabase();
        assertTrue(db.isOpen());

        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue("Error: Database not created correctly!", cursor.moveToFirst());

        // verify if the tables were created
        do {
            tables.remove(cursor.getString(0));
        } while ( cursor.moveToNext() );

        assertTrue("Database created without tables!", tables.isEmpty());

        // verify if tables contain the correct columns
        cursor = db.rawQuery("PRAGMA table_info(" + DBHelper.METEORITE_TABLE + ")", null);

        assertTrue("Error: unable to query the database for table information.", cursor.moveToFirst());

        final Set<String> meteoriteHashSet = new HashSet<>();
        meteoriteHashSet.add(DBHelper.COLUMN_ID);
        meteoriteHashSet.add(DBHelper.COLUMN_FALL);
        meteoriteHashSet.add(DBHelper.COLUMN_TYPE);
        meteoriteHashSet.add(DBHelper.COLUMN_LATITUDE);
        meteoriteHashSet.add(DBHelper.COLUMN_LONGITUDE);
        meteoriteHashSet.add(DBHelper.COLUMN_MASS);
        meteoriteHashSet.add(DBHelper.COLUMN_NAME);
        meteoriteHashSet.add(DBHelper.COLUMN_NAMETYPE);
        meteoriteHashSet.add(DBHelper.COLUMN_RECCLASS);
        meteoriteHashSet.add(DBHelper.COLUMN_RECLAT);
        meteoriteHashSet.add(DBHelper.COLUMN_RECLONG);
        meteoriteHashSet.add(DBHelper.COLUMN_YEAR);

        int columnNameIndex = cursor.getColumnIndex("name");
        do {
            String columnName = cursor.getString(columnNameIndex);
            meteoriteHashSet.remove(columnName);
        } while ( cursor.moveToNext() );

        assertTrue("Error: this mean that database doesn't contain all of required columns", meteoriteHashSet.isEmpty());

        cursor.close();
        db.close();
    }
}
