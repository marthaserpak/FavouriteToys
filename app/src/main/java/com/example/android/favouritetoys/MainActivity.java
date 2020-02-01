package com.example.android.favouritetoys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.favouritetoys.NetworkUtils.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText mSearchBoxEditText;
    TextView mUrlDisplaytextView;
    TextView mSearchResultsTextView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        /* Return true to display menu */
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*TextView toysListTextView = findViewById(R.id.tv_toy_names);*/
        mSearchBoxEditText = findViewById(R.id.et_search_box);
        mUrlDisplaytextView = findViewById(R.id.tv_url_display);
        mSearchResultsTextView = findViewById(R.id.tv_gitgub_search_results);

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

    void makeGithubSearchQuery(){
        /* Within this method, build the URL with the text
        from the EditText and set the built URL to the TextView */
        String githubQuery = mSearchBoxEditText.getText().toString();
        /* It pulls the githubQuery from the Edit Text, calls the
        * buildUrl() method .*/
        URL gitgubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        /* and displays the resulting URL in the UrlDisplayTextView. */
        mUrlDisplaytextView.setText(gitgubSearchUrl.toString());
    }
}
        /*Use the static ToyBox.getToyNames
        method and store the names in a String array*/
    //String[] toyNames = ToyBox.getToysNames();

        /*Loop through each toy and
        append the name to the TextView */

/*   for ( String toyName : toyNames) {
            toysListTextView.append(toyName + "\n\n\n");
        }
*/

