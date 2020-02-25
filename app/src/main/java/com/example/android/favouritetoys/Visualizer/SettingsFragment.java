package com.example.android.favouritetoys.Visualizer;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.android.favouritetoys.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {

        //this method generate a preference screen from that file.
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
