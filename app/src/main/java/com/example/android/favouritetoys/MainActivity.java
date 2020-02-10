package com.example.android.favouritetoys;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;

    /* References to Recycler View and Adapter to reset the list to it's
    * "pretty" state when the rest menu items is clicked. */
    private GreenAdapter  mAdapter;
    private RecyclerView mNumberList;

    private Toast mToast;

    private EditText mNameEntry;
    private Button mSendbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEntry = findViewById(R.id.et_text_entry);
        mSendbutton = findViewById(R.id.send_bt);

        mSendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Entering the text from Ed.Txt and store it in a variable */
                String textEntered = mNameEntry.getText().toString();

                /*
                 * Storing the Context in a variable in this case is redundant
                 * since we could have just used "this" or "MainActivity.this"
                 * in the method call below. However, we wanted to demonstrate
                 * what parameter we were using "MainActivity.this" for as
                 * clear as possible.
                 */
                Context context = MainActivity.this;

                /* This is the class that we want to start
                 (and open) when the button is clicked. */
                Class dstinationActivity = ChildActivity.class;

                Intent startChildActivity =  new Intent(context, dstinationActivity);

                /*
                 * We use the putExtra method of the Intent class to pass some extra stuff to the
                 * Activity that we are starting. Generally, this data is quite simple, such as
                 * a String or a number. However, there are ways to pass more complex objects.
                 */
                startChildActivity.putExtra(Intent.EXTRA_TEXT, textEntered);

                startActivity(startChildActivity);
            }
        });

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


