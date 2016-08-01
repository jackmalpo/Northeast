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
        //Dev Test 1
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        //Dev 2

        //Rebase 1
    }
}
