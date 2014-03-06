package com.spotifystream.sptofyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spotifystream.sptofyapp.R;


public class MainStreamFragment extends Fragment {

    public MainStreamFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_main_stream,null);
    }
}
