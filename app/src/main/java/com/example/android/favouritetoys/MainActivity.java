 package com.example.android.favouritetoys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         TextView toysListTextView = findViewById(R.id.tv_toy_names);

        /*Use the static ToyBox.getToyNames
        method and store the names in a String array*/
        String[] toyNames = ToyBox.getToysNames();

        /*Loop through each toy and
        append the name to the TextView */
        for ( String toyName : toyNames) {
            toysListTextView.append(toyName + "\n\n\n");
        }

    }
}
