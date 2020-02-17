package com.example.android.favouritetoys.AsyncTaskLoader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.favouritetoys.AsyncTaskLoader.Utilities.NetworkUtils;
import com.example.android.favouritetoys.R;

import java.io.IOException;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {
    //String to store query's url
    public static final String QUERY_URL = "queryUrl";
    //Key to store search's raw JSON
    public static final String RAW_JSON = "rawJSON";


    private EditText mSearchBox;

    private TextView mUrlDisplay;
    private TextView mSearchResults;

    private TextView mErrorMessage;

    private ProgressBar mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mSearchBox = findViewById(R.id.et_search_box);
        mUrlDisplay = findViewById(R.id.tv_url_display);
        mSearchResults = findViewById(R.id.tv_github_search_results_json);
        mErrorMessage = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        //If the savedInstanceState bundle is not null, set
        // the text of the URL and search results TextView respectively
        if(savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(QUERY_URL);
            String rawJsonSearchResults = savedInstanceState
                    .getString(RAW_JSON);

            mUrlDisplay.setText(queryUrl);
            mSearchResults.setText(rawJsonSearchResults);
        }
    }

    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBox.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        new GithubQueryTask().execute(githubSearchUrl);
    }

    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView() {
        /* First, make sure the error is invisible */
        mErrorMessage.setVisibility(View.INVISIBLE);
        /* Then, make sure the JSON data is visible */
        mSearchResults.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mSearchResults.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    @SuppressLint("StaticFieldLeak")
    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                showJsonDataView();
                mSearchResults.setText(githubSearchResults);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Override onSaveInstanceState to persist data
     across Activity recreation (для сохранения данных при
     обновлении).*/
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //Put the contents of the TextView that contains our URL into a variable
        String queryUrl = mUrlDisplay.getText().toString();
        // Using the key for the query URL,
        // put the string in the outState Bundle
        outState.putString(QUERY_URL, queryUrl);

        //Put the contents of the TextView that contains our raw JSON search results into a variable
        String rawJsonSearchResults = mSearchResults.getText().toString();

        //Using the key for the raw JSON search results,
        // put the search results into the outState Bundle
        outState.putString(RAW_JSON, rawJsonSearchResults);
    }
}
