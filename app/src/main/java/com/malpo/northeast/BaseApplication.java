package com.malpo.northeast;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Jack on 2/25/16.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
