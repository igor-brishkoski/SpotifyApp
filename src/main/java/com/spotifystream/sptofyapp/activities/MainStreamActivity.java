package com.spotifystream.sptofyapp.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.spotifystream.sptofyapp.R;
import com.spotifystream.sptofyapp.fragments.MainStreamFragment;
import com.spotifystream.sptofyapp.models.Track;

public class MainStreamActivity extends ActionBarActivity implements MainStreamFragment.OnAlbumTappedListener {

    private final String TAG = MainStreamActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stream);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainStreamFragment())
                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_stream, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //fragment interaction
    @Override
    public void onAlbumSelected(Track selectedTrack) {
        Toast.makeText(this,selectedTrack.toString(),Toast.LENGTH_SHORT).show();
        Log.d(TAG, "CLICKED "+selectedTrack.toString());
    }


}
