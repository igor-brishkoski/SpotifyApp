package com.spotifystream.sptofyapp.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.spotifystream.sptofyapp.Adapters.MainStreamGridAdapter;
import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.fundamentals.SpotifyApp;
import com.spotifystream.sptofyapp.fundamentals.SpotifyConstants;
import com.spotifystream.sptofyapp.listeners.OnMainStreamScrollListener;
import com.spotifystream.sptofyapp.models.Track;

import java.util.ArrayList;


public class MainStreamFragment extends Fragment {

    private final String TAG = MainStreamFragment.class.getSimpleName();
    ArrayList<Track> tracks;
    MainStreamGridAdapter mainStreamGridAdapter;
    GridView mainStreamGridView;
    OnAlbumTappedListener albumTappedListener;

    SharedPreferences sharedPreferences;

    public MainStreamFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = SpotifyApp.getSharedPreferences();

        tracks = new ArrayList<Track>();
        mainStreamGridAdapter = new MainStreamGridAdapter(tracks);
        readJson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_stream, null);

        mainStreamGridView = (GridView) view.findViewById(R.id.gv_mainstream);
        mainStreamGridView.setAdapter(mainStreamGridAdapter);
        mainStreamGridView.setOnScrollListener(new OnMainStreamScrollListener(this));

        mainStreamGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                albumTappedListener.onAlbumSelected(tracks.get(position));
            }
        });


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            albumTappedListener = (OnAlbumTappedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentSelectedImageListener");
        }
    }

    public void readJson() {
        final Gson gson = new Gson();

        Ion.with(getActivity(), SpotifyConstants.SPOTIFY_MOSTSTREAMED_URL + sharedPreferences.getString(SpotifyConstants.CURRENT_STREAM_DATE, SpotifyConstants.LATEST))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ArrayList<Track> newtracks = new ArrayList<Track>();
                        Log.d(TAG, result.toString());
                        JsonArray jsonArray = result.getAsJsonArray("tracks");

                        for (int i = 0; i < jsonArray.size(); i++) {
                            Log.d(TAG, jsonArray.get(i).toString());
                            newtracks.add(gson.fromJson(jsonArray.get(i), Track.class));
                        }

                        sharedPreferences.edit()
                                .putString(SpotifyConstants.CURRENT_STREAM_DATE, result.get("prevDate").getAsString())
                                .putBoolean(SpotifyConstants.LOADING_NEW_ALBUMS, true)
                                .commit();

                        mainStreamGridAdapter.refersh(newtracks);

                        Log.d(TAG, SpotifyConstants.SPOTIFY_MOSTSTREAMED_URL + sharedPreferences.getString(SpotifyConstants.CURRENT_STREAM_DATE, "NO"));
                    }

                });
    }

    public interface OnAlbumTappedListener {
        public void onAlbumSelected(Track selectedTrack);
    }
}
