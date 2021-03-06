package com.example.android.favouritetoys.Visualizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.favouritetoys.R;
import com.example.android.favouritetoys.Visualizer.AudioVisuals.AudioInputReader;
import com.example.android.favouritetoys.Visualizer.AudioVisuals.VisualizerView;

public class VisualizerActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener  {

    private static final int MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE = 88;
    private VisualizerView mVisualizerView;
    private AudioInputReader mAudioInputReader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizer2);
        mVisualizerView = findViewById(R.id.activity_visualizer2);
        setupSharedPreferences();
        setupPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }


    private void setupSharedPreferences() {

        //Get a reference to the default shared preferences
        // from the PreferenceManager class
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);

        //get value of the show bass check box preference and use it to call setShowBass
        mVisualizerView.setShowBass(sharedPreferences.getBoolean(
                getString(R.string.pref_show_bass_key),
                getResources().getBoolean(R.bool.press_show_bass_default)));
        mVisualizerView.setShowMid(sharedPreferences.getBoolean(
                getString(R.string.pref_show_mid_key),
                getResources().getBoolean(R.bool.press_show_mid_default)));
        mVisualizerView.setShowTreble(sharedPreferences.getBoolean(getString(R.string.pref_show_treble_key),
                getResources().getBoolean(R.bool.press_show_treble_default)));
        mVisualizerView.setMinSizeScale(1);
        loadColorFromPreferences(sharedPreferences);
        loadSizeFromSharedPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void loadSizeFromSharedPreferences(SharedPreferences sharedPreferences) {
        float minSize = Float.parseFloat(sharedPreferences.getString(getString(R.string.pref_size_key),
                getString(R.string.pref_size_default)));
        mVisualizerView.setMinSizeScale(minSize);
    }

    public void loadColorFromPreferences(SharedPreferences sharedPreferences){
        mVisualizerView.setColor(sharedPreferences.getString(getString(R.string.pref_color_key),
                getString(R.string.pref_color_red_value)));
    }

    /*
     * Below this point is code you do not need to modify; it deals with permissions
     * and starting/cleaning up the AudioInputReader
     */

    /**
     * onPause Cleanup audio stream
     **/
    @Override
    protected void onPause() {
        super.onPause();
        if (mAudioInputReader != null) {
            mAudioInputReader.shutdown(isFinishing());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAudioInputReader != null) {
            mAudioInputReader.restart();
        }
    }

    /**
     * App Permissions for Audio
     **/
    private void setupPermissions() {
        // If we don't have the record audio permission...
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            // And if we're on SDK M or later...
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Ask again, nicely, for the permissions.
                String[] permissionsWeNeed = new String[]{Manifest.permission.RECORD_AUDIO};
                requestPermissions(permissionsWeNeed, MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE);
            }
        } else {
            // Otherwise, permissions were granted and we are ready to go!
            mAudioInputReader = new AudioInputReader(mVisualizerView, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // The permission was granted! Start up the visualizer!
                    mAudioInputReader = new AudioInputReader(mVisualizerView, this);

                } else {
                    Toast.makeText(this, "Permission for audio not granted. Visualizer can't run.", Toast.LENGTH_LONG).show();
                    finish();
                    // The permission was denied, so we can show a message why we can't run the app
                    // and then close the app.
                }
            }
            // Other permissions could go down here

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.visualizer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startedSettingsActivity = new Intent(
                    VisualizerActivity.this,
                    SettingsActivity.class);
            startActivity(startedSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_show_bass_key))) {
            mVisualizerView.setShowBass(sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.press_show_bass_default)));
        } else if (key.equals(getString(R.string.pref_show_mid_key))) {
            mVisualizerView.setShowMid(sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.press_show_mid_default)));
        } else if (key.equals(getString(R.string.pref_show_treble_key))){
            mVisualizerView.setShowTreble(sharedPreferences.getBoolean(key,
                    getResources().getBoolean(R.bool.press_show_treble_default)));
        } else if(key.equals(getString(R.string.pref_color_key))){
            loadColorFromPreferences(sharedPreferences);
        } else if(key.equals(getString(R.string.pref_size_key))){
            loadSizeFromSharedPreferences(sharedPreferences);
        }
    }
}
