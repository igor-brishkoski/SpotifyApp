package com.spotifystream.sptofyapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.customizedviews.TypeFacedTextView;
import com.spotifystream.sptofyapp.models.AlbumTracks;
import com.spotifystream.sptofyapp.models.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bri6ko on 3/6/14.
 */
public class AlbumTracksListViewAdapter extends ArrayAdapter<AlbumTracks> {

    private static final String TAG = AlbumTracksListViewAdapter.class.getSimpleName();
    private Context context;
    private int layout_resource;
    private ArrayList<AlbumTracks> albumTracks;

    public AlbumTracksListViewAdapter(Context context, int resource, List<AlbumTracks> albumTracks) {
        super(context, resource, albumTracks);
        this.context = context;
        this.layout_resource = resource;
        this.albumTracks = (ArrayList<AlbumTracks>) albumTracks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout_resource,null);
        }

        TypeFacedTextView trackName = (TypeFacedTextView) convertView.findViewById(R.id.tv_item_track);
        trackName.setText(albumTracks.get(position).getName());

        return convertView;
    }

    public void refersh(ArrayList<AlbumTracks> newTracks) {
        //tracks.clear();
        albumTracks.addAll(newTracks);
        notifyDataSetChanged();
        Log.d(TAG, "TOtal tracks" + albumTracks.size());
    }
}
