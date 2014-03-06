package com.spotifystream.sptofyapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.fundamentals.SpotifyApp;
import com.spotifystream.sptofyapp.models.Track;

import java.util.ArrayList;


public class MainStreamFragment extends Fragment {

    private final String TAG = MainStreamFragment.class.getSimpleName();
    ArrayList<Track> tracks;

    public MainStreamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tracks = new ArrayList<Track>();
        readJson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_stream,null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void readJson(){
        final Gson gson = new Gson();

        Ion.with(getActivity(), SpotifyApp.SPOTIFY_URL + "latest")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        Log.d(TAG, result.toString());
                        JsonArray jsonArray = result.getAsJsonArray("tracks");

                        for(int i=0;i< jsonArray.size();i++){
                            Log.d(TAG, jsonArray.get(i).toString());
                            tracks.add(gson.fromJson(jsonArray.get(i),Track.class));
                        }
                        Log.d(TAG, result.get("prevDate").toString());
                    }
                });
    }

    public interface onAlbumTappedListener{
        public void onAlbumSelected(String id);
    }
}
