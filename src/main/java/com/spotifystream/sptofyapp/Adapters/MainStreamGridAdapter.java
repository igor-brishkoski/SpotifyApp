package com.spotifystream.sptofyapp.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.spotifystream.sptofyapp.models.Track;

import java.util.ArrayList;

public class MainStreamGridAdapter extends BaseAdapter {
    ArrayList<Track> tracks = new ArrayList<Track>();

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
        //TODO implement this shit
        return null;
    }
}
