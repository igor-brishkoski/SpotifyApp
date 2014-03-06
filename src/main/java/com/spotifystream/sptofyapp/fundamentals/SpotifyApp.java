package com.spotifystream.sptofyapp.fundamentals;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SpotifyApp extends Application{

    private final String TAG = SpotifyApp.class.getSimpleName();


    private static SharedPreferences sharedPreferences;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        context = this;

        sharedPreferences.edit()
                .putString(SpotifyConstants.CURRENT_STREAM_DATE, SpotifyConstants.LATEST)
                .commit();

    }

    /**
     * @return apps default SharedPrefrences
     */
    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * @return the app Context
     */
    public static Context getContext() {
        return context;
    }


}
