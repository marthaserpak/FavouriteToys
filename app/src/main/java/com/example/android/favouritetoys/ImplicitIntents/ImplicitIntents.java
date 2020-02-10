package com.example.android.favouritetoys.ImplicitIntents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.favouritetoys.R;

public class ImplicitIntents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.implicit_intents);

        Button openWebsiteButton = findViewById(R.id.open_website);
        Button locationMapButton = findViewById(R.id.open_addres_button);
        Button shareTextButton = findViewById(R.id.share_text_button);
        Button yourOwnButton = findViewById(R.id.create_your_own);

        openWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAsString = "https://classroom.udacity.com/me";
                openWebpage(urlAsString);
            }
        });

        locationMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressString = "1600 Amphitheater Parkway, CA";

                /* Use Uri.Builder with the appropriate scheme and query to form
                   the Uri for the address */

                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .path("0,0")
                        .appendQueryParameter("q", addressString);
                Uri addressUri = builder.build();

                showMap(addressUri);
            }

        });

        shareTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        yourOwnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    /* This method expects() a Url  for the website that i'd like to open as a String. */
    public void openWebpage(String url) {
        /*
         * A URI is like an address that points to the data
         * you're planning to pass through to the intended action
         *
         * In this case our web page should be parsed into a URI. */
        Uri webpage = Uri.parse(url);

        /*Then we create an Intent and pass URI*/
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method will fire off an implicit Intent to view a location on a map.
     * <p>
     * When constructing implicit Intents, you can use either the setData method
     * or specify the URI as the second parameter of the Intent's constructor
     *
     * @param geoLocation The Uri representing the location that will be opened in the map
     */
    public void showMap(Uri geoLocation) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
