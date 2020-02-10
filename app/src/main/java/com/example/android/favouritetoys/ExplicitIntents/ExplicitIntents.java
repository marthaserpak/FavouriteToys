package com.example.android.favouritetoys.ExplicitIntents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.favouritetoys.R;
import com.example.android.favouritetoys.RecyclerView.ChildActivity;

public class ExplicitIntents extends AppCompatActivity {

    private EditText mNameEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intents);

        mNameEntry = findViewById(R.id.et_text_entry);
        Button sendbutton = findViewById(R.id.send_bt);

        sendbutton.setOnClickListener(new View.OnClickListener() {
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
                Context context = ExplicitIntents.this;

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
    }
}
