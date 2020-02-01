package com.example.android.favouritetoys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.favouritetoys.NetworkUtils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText mSearchBoxEditText;
    TextView mUrlDisplayTextView;
    TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*TextView toysListTextView = findViewById(R.id.tv_toy_names);*/
        mSearchBoxEditText = findViewById(R.id.et_search_box);
        mUrlDisplayTextView = findViewById(R.id.tv_url_display);
        mSearchResultsTextView = findViewById(R.id.tv_gitgub_search_results);
    }

    private void makeGithubSearchQuery() {
        /*
         * Within this method, build the URL with the text
         * from the EditText and set the built URL to the TextView .
         * */
        String githubQuery = mSearchBoxEditText.getText().toString();
        /*
         * It pulls the githubQuery from the Edit Text, calls the
         * buildUrl() method.
         * */
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        /* and displays the resulting URL in the UrlDisplayTextView. */
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        /* Call getResponseFromHttpUrl and display the results
         * in mSearchResultsTextView.
         */
        /*String githubSearchResults = null;
        try {
            githubSearchResults =
                    NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
            mSearchResultsTextView.setText(githubSearchResults);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        new GitHubQueryTask().execute(githubSearchUrl);
    }

    @SuppressLint("StaticFieldLeak")
    public class GitHubQueryTask extends AsyncTask<URL, Void, String> {
        /*Override the doInBackground method
            to perform the query. Return the results.
            */
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String githubSearchResults = null;
            try {
                githubSearchResults =
                        NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            if (githubSearchResults != null && !
                    githubSearchResults.equals("")) {
                mSearchResultsTextView.setText(githubSearchResults);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        /* Return true to display menu */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /* Menu items identify by integer IDs , so first,
         * i'll grab the selected item's ID.*/
        int menuItemThatWasSelected = item.getItemId();
        if (menuItemThatWasSelected == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


