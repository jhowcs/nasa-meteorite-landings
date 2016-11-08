package com.jhowcs.nasameteoritelandings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jhowcs.nasameteoritelandings.data.DBContract;
import com.jhowcs.nasameteoritelandings.data.DBHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        deleteDatabase();
    }

    @Test
    public void deleteDatabase() {
        mContext.deleteDatabase(DBContract.MeteorLandEntry.TABLE_NAME);
    }

    @Test
    public void createDatabase() {
        final Set<String> tables = new HashSet<>();
        tables.add(DBContract.MeteorLandEntry.TABLE_NAME);

        deleteDatabase();

        SQLiteDatabase db = getDatabaseInstance();
        assertTrue(db.isOpen());

        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue("Error: Database not created correctly!", cursor.moveToFirst());

        // verify if the tables were created
        do {
            tables.remove(cursor.getString(0));
        } while ( cursor.moveToNext() );

        assertTrue("Database created without tables!", tables.isEmpty());

        // verify if tables contain the correct columns
        cursor = db.rawQuery("PRAGMA table_info(" + DBContract.MeteorLandEntry.TABLE_NAME + ")", null);

        assertTrue("Error: unable to query the database for table information.", cursor.moveToFirst());

        final Set<String> meteoriteHashSet = new HashSet<>();
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_ID);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_FALL);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_TYPE);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_LATITUDE);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_LONGITUDE);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_MASS);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_NAME);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_NAMETYPE);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_RECCLASS);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_RECLAT);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_RECLONG);
        meteoriteHashSet.add(DBContract.MeteorLandEntry.COLUMN_YEAR);

        int columnNameIndex = cursor.getColumnIndex("name");
        do {
            String columnName = cursor.getString(columnNameIndex);
            meteoriteHashSet.remove(columnName);
        } while ( cursor.moveToNext() );

        assertTrue("Error: this mean that database doesn't contain all of required columns", meteoriteHashSet.isEmpty());

        cursor.close();
        db.close();
    }

    @Test
    public void insertMeteoriteLanding() {
        SQLiteDatabase db = getDatabaseInstance();

        ContentValues cv = new ContentValues();

        cv.put(DBContract.MeteorLandEntry.COLUMN_ID, 56661);
        cv.put(DBContract.MeteorLandEntry.COLUMN_FALL, "Found");
        cv.put(DBContract.MeteorLandEntry.COLUMN_TYPE, "Point");
        cv.put(DBContract.MeteorLandEntry.COLUMN_LATITUDE, "-24.85");
        cv.put(DBContract.MeteorLandEntry.COLUMN_LONGITUDE, "-70.53333");
        cv.put(DBContract.MeteorLandEntry.COLUMN_MASS, "84");
        cv.put(DBContract.MeteorLandEntry.COLUMN_NAME, "El Médano 115");
        cv.put(DBContract.MeteorLandEntry.COLUMN_NAMETYPE, "Valid");
        cv.put(DBContract.MeteorLandEntry.COLUMN_RECCLASS, "H5");
        cv.put(DBContract.MeteorLandEntry.COLUMN_RECLAT, "-24.850000");
        cv.put(DBContract.MeteorLandEntry.COLUMN_RECLONG, "-70.533330");
        cv.put(DBContract.MeteorLandEntry.COLUMN_YEAR, "2011-01-01T00:00:00.000");

        // insert a row in the database
        long idReturned = db.insert(DBContract.MeteorLandEntry.TABLE_NAME, null, cv);

        assertTrue("Error: some problem happen when tried to insert a row.", idReturned != -1);

        // Query the database
        Cursor cursor = db.query(
                DBContract.MeteorLandEntry.TABLE_NAME,
                null, // all columns
                null, // where clause
                null, // values for where clause
                null, // group by columns
                null, // columns to filter by row groups
                null); // sort order

        assertTrue("Error: no records returned.", cursor.moveToFirst());

        // validate cursor returned with the original ContentValues
        validateCurrentRecord("Error: query validation failed.", cursor, cv);

        // Verify if cursor returned more than one record
        assertFalse("Error: more than one record returned", cursor.moveToNext());

        cursor.close();
        db.close();
    }

    /**
     * Create an instance of our Meteorite database
     *
     * @return Writable instance of database
     */
    private SQLiteDatabase getDatabaseInstance() {
        DBHelper dbHelper = new DBHelper(mContext);

        return dbHelper.getWritableDatabase();
    }

    private void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();

        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }
    }
}
