package com.jhowcs.nasameteoritelandings.data.datasync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.data.DBContract;
import com.jhowcs.nasameteoritelandings.data.entity.NasaMeteoriteLandings;
import com.jhowcs.nasameteoritelandings.data.network.api.MeteoriteAPI;
import com.jhowcs.nasameteoritelandings.data.repository.MeteoriteLandingsRepository;

import java.util.List;

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
        MeteoriteAPI meteoriteAPI = new MeteoriteAPI();

        List<NasaMeteoriteLandings> response = meteoriteAPI.getNasaMeteoriteLandings();

        if(response != null) {
            Cursor cursor = null;

            MeteoriteLandingsRepository repository = new MeteoriteLandingsRepository(getContext());

            for (NasaMeteoriteLandings obj: response) {
                cursor = mContentResolver.query(DBContract.MeteorLandEntry.CONTENT_URI,
                        null,
                        DBContract.MeteorLandEntry.COLUMN_ID + " = " + obj.getId(),
                        null,null);

                if(cursor != null && cursor.getCount() <=0) {
                    cursor.close();

                    repository.insert(obj);

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
