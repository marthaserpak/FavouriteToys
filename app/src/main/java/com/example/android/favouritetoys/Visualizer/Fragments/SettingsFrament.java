package com.example.android.favouritetoys.Visualizer.Fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.android.favouritetoys.R;

public class SettingsFrament extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
