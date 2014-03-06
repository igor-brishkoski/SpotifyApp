package com.spotifystream.sptofyapp.listeners;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.widget.AbsListView;

import com.spotifystream.sptofyapp.fragments.MainStreamFragment;
import com.spotifystream.sptofyapp.fundamentals.SpotifyApp;
import com.spotifystream.sptofyapp.fundamentals.SpotifyConstants;


public class OnMainStreamScrollListener implements AbsListView.OnScrollListener{
    private final String TAG = OnMainStreamScrollListener.class.getSimpleName();

    SharedPreferences sharedPreferences;
    MainStreamFragment mainStreamFragment;

    public OnMainStreamScrollListener(Fragment fragment) {
        this.sharedPreferences = SpotifyApp.getSharedPreferences();
        mainStreamFragment = (MainStreamFragment) fragment;

        sharedPreferences.edit()
                .putBoolean(SpotifyConstants.LOADING_NEW_ALBUMS, true)
                .commit();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if((firstVisibleItem+visibleItemCount)>totalItemCount-5 && totalItemCount!=0 && sharedPreferences.getBoolean(SpotifyConstants.LOADING_NEW_ALBUMS,false)){
            sharedPreferences.edit()
                    .putBoolean(SpotifyConstants.LOADING_NEW_ALBUMS,false)
                    .commit();
            mainStreamFragment.readJson();
        }
    }
}
