package com.example.android.favouritetoys;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int NUM_LIST_ITEMS = 100;

    /* References to Recycler View and Adapter to reset the list to it's
    * "pretty" state when the rest menu items is clicked. */
    private GreenAdapter  mAdapter;
    private RecyclerView mNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}


