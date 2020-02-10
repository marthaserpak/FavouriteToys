package com.example.android.favouritetoys.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.android.favouritetoys.R;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;

    /* References to Recycler View and Adapter to reset the list to it's
    * "pretty" state when the rest menu items is clicked. */
    private GreenAdapter  mAdapter;
    private RecyclerView mNumberList;

    private Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /* Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility. */
        mNumberList = findViewById(R.id.rv_numbers);

        /* A LinearLayoutManager is responsible for(ответственный за)
         * measuring(измерение) and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumberList.setLayoutManager(layoutManager);
        mNumberList.setHasFixedSize(true);

        /* The GreenAdapter is responsible for displaying each item in the list. */
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);

        mNumberList.setAdapter(mAdapter);

    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        if(mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked!";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        mToast.show();
    }
}


