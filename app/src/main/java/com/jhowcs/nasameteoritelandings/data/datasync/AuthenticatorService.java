package com.jhowcs.nasameteoritelandings.data.datasync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by jonathan_campos on 03/11/2016.
 *
 * Stub just to make SyncAdapter work
 */

public class AuthenticatorService extends Service {

    private Authenticator mAuthenticator;

    public AuthenticatorService() {
        mAuthenticator = new Authenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
