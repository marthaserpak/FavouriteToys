package com.example.android.favouritetoys.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.favouritetoys.R;

public class ChildActivity extends AppCompatActivity {

    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mDisplay = findViewById(R.id.tv_display);


        /*
         * Use the getIntent method to store the Intent that started this Activity in a variable.
         *
         * Here is where all the magic happens. The getIntent method will give us the Intent that
         * started this particular Activity.
         */
        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String textEntered = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplay.setText(textEntered);
        }

    }
}
