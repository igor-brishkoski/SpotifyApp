package com.spotifystream.sptofyapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.fundamentals.SpotifyApp;
import com.spotifystream.sptofyapp.models.Track;

import java.util.ArrayList;

public class MainStreamGridAdapter extends BaseAdapter {
    private static final String TAG = MainStreamGridAdapter.class.getSimpleName();

    ArrayList<Track> tracks = new ArrayList<Track>();

    public MainStreamGridAdapter(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) SpotifyApp.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.track_layout,null);
        }
            ImageView albumArt = (ImageView) convertView.findViewById(R.id.iv_mainstream_album_art);
            loadAlbumArt(albumArt, tracks.get(position).getArtwork_url());

        return convertView;
    }

    private void loadAlbumArt(ImageView imageView, String url){
        Ion.with(imageView)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .load(url);
    }

    public void refersh(ArrayList<Track> newTracks) {
        //tracks.clear();
        tracks.addAll(newTracks);
        notifyDataSetChanged();
        Log.d(TAG, "TOtal tracks"+tracks.size());
    }
}
