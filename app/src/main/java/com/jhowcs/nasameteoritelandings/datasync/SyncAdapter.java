package com.jhowcs.nasameteoritelandings.datasync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.jhowcs.nasameteoritelandings.BuildConfig;
import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.data.DBContract;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.service.BaseAPI;
import com.jhowcs.nasameteoritelandings.service.MeteoriteLandingService;
import com.jhowcs.nasameteoritelandings.util.DateUtil;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jonathan_campos on 03/11/2016.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG = "SyncAdapter";

    private ContentResolver mContentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        mContentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d(TAG, "onPerformSync");

        synchronizeMeteorite();

        mContentResolver.notifyChange(DBContract.MeteorLandEntry.CONTENT_URI, null);
    }

    /**
     * This method load information from Nasa's WebServices and synchronize with our app's database.
     *
     */
    private void synchronizeMeteorite() {
        Retrofit retrofit = BaseAPI.getRetrofitInstance();
        MeteoriteLandingService meteoriteLandingService = retrofit.create(MeteoriteLandingService.class);

        String dateQuery = DateUtil.getCompleteStringDate(2011);

        Call<List<NasaMeteoriteLandings>> call = meteoriteLandingService.getNasaMeteoriteLandings(dateQuery, BuildConfig.NASA_API);

        Response<List<NasaMeteoriteLandings>> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response != null) {
            Cursor cursor = null;

            for (NasaMeteoriteLandings obj: response.body()) {
                cursor = mContentResolver.query(DBContract.MeteorLandEntry.CONTENT_URI,
                        null,
                        DBContract.MeteorLandEntry.COLUMN_ID + " = " + obj.getId(),
                        null,null);

                if(cursor != null && cursor.getCount() <=0) {
                    cursor.close();

                    ContentValues cv = new ContentValues();
                    cv.put(DBContract.MeteorLandEntry.COLUMN_ID, obj.getId());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_FALL, obj.getFall());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_TYPE, obj.getGeolocation().getType());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_LATITUDE, obj.getGeolocation().getCoordinates().get(1));
                    cv.put(DBContract.MeteorLandEntry.COLUMN_LONGITUDE, obj.getGeolocation().getCoordinates().get(0));
                    cv.put(DBContract.MeteorLandEntry.COLUMN_MASS, obj.getMass());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_NAME, obj.getName());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_NAMETYPE, obj.getNametype());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_RECCLASS, obj.getRecclass());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_RECLAT, obj.getReclat());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_RECLONG, obj.getReclong());
                    cv.put(DBContract.MeteorLandEntry.COLUMN_YEAR, obj.getYear());

                    mContentResolver.insert(DBContract.MeteorLandEntry.CONTENT_URI, cv);

                    Log.d(TAG, "Saving new record");
                }
            }
        }
    }

    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    private static Account getSyncAccount(Context context) {
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        Account account = new Account(context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        if(accountManager.getPassword(account) == null) {
            if(!accountManager.addAccountExplicitly(account, "" , null)) {
                return null;
            }
        }

        return account;
    }
}
