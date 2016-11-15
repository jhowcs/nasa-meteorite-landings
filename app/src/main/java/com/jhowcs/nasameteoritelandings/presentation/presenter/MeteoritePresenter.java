package com.jhowcs.nasameteoritelandings.presentation.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.jhowcs.nasameteoritelandings.data.DBContract;
import com.jhowcs.nasameteoritelandings.presentation.view.MainView;

/**
 * Created by jonathan_campos on 25/10/2016.
 */

public class MeteoritePresenter implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "MeteoritePresenter";

    private static final int LOADER_ID = 1;

    private MainView mMainView;
    private LoaderManager mLoaderManager;
    private Context mContext;

    public MeteoritePresenter(MainView mainView, LoaderManager loaderManager, Context context) {
        mMainView = mainView;
        mLoaderManager = loaderManager;
        mContext = context;

        mLoaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        mMainView.onShowProgress();
        return new CursorLoader(mContext, DBContract.MeteorLandEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "onLoadFinished");
        mMainView.onHideProgress();

        mMainView.renderMeteoriteList(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mMainView.renderMeteoriteList(null);
    }

    public void retry() {
        mLoaderManager.restartLoader(LOADER_ID, null, this);
    }
}
