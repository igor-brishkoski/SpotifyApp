package com.spotifystream.sptofyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.spotifystream.sptofyapp.Adapters.AlbumTracksListViewAdapter;
import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.fundamentals.SpotifyConstants;
import com.spotifystream.sptofyapp.models.AlbumTracks;
import com.spotifystream.sptofyapp.models.Track;

import java.net.URI;
import java.util.ArrayList;


public class AlbumArtFragment extends Fragment {

    private static final String TAG = AlbumArtFragment.class.getSimpleName();
    Gson gson;

    ImageView albumArt;
    ListView albumTracksListView;

    TextView albumName;
    TextView artistName;
    TextView albumReleased;

    ArrayList<AlbumTracks> albumTrackses;
    AlbumTracksListViewAdapter albumTracksListViewAdapter;

    public AlbumArtFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        albumTrackses = new ArrayList<AlbumTracks>();
        albumTracksListViewAdapter = new AlbumTracksListViewAdapter(getActivity(),R.layout.track_item,albumTrackses);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        String details = getArguments().getString(SpotifyConstants.JSON_DETAILS);
        Track track = gson.fromJson(details,Track.class);
        URI uri = URI.create(track.getAlbum_url());
        String id = uri.getPath();
        String albumId = id.substring(id.lastIndexOf("/") + 1);
        loadAlbumTracks(albumId);

        albumArt = (ImageView) rootView.findViewById(R.id.iv_details_albumart);
        albumTracksListView = (ListView) rootView.findViewById(R.id.lv_details_tracks);
        albumName = (TextView) rootView.findViewById(R.id.tv_details_albumname);
        artistName = (TextView) rootView.findViewById(R.id.tv_details_artistname);
        albumReleased = (TextView) rootView.findViewById(R.id.tv_details_realesed);

        albumName.setText(track.getAlbum_name());
        artistName.setText(track.getArtist_name());
        albumReleased.setText(track.getDate());

        albumTracksListView.setAdapter(albumTracksListViewAdapter);

        loadAlbumArt(albumArt, track.getArtwork_url());
        //Log.d(TAG, id.substring(id.lastIndexOf("/")+1));




        return rootView;
    }

    public void loadAlbumTracks(String album){

        Ion.with(getActivity(),SpotifyConstants.SPOIFY_TRACKEXTRAS_URL+album+"&extras=trackdetail")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ArrayList<AlbumTracks> newtracks = new ArrayList<AlbumTracks>();
                        Log.d(TAG,result.get("album").getAsJsonObject().get("tracks").toString());
                        JsonArray array = result.get("album").getAsJsonObject().get("tracks").getAsJsonArray();

                        for(int i=0;i<array.size();i++){
                            newtracks.add(gson.fromJson(array.get(i),AlbumTracks.class));
                        }
                        albumTracksListViewAdapter.refersh(newtracks);

                        for(AlbumTracks track: albumTrackses)
                            Log.d(TAG,track.toString());
                    }
                });
    }

    private void loadAlbumArt(ImageView imageView, String url) {
        Ion.with(imageView)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .load(url);
    }



}
