package com.example.android.favouritetoys.Visualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.favouritetoys.R;
import com.example.android.favouritetoys.Visualizer.Utils.VisualizerView;

public class VisualizerActivity extends AppCompatActivity {

    private static final int  MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE =  88;
    private VisualizerView mVisualizerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizer);
    }
}
