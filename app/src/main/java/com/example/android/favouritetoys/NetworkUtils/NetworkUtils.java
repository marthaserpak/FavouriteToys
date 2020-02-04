package com.example.android.favouritetoys.NetworkUtils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/* These utils will be use to communicate with the network. */
public class NetworkUtils {

    private final static String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";
    private final static String PARAM_QUERY = "q";

    /* The sort field.(Сортировка поля) One of stars, forks, or updated.
     *
     * Default: results are sorted by best match if no field is specified.*/
    private final static String PARAM_SORT = "sort";
    private final static String sortBy = "stars";

    /* Builds the URL used to query GitHub.
     *
     * @param githubSearchQuery the keyword that'll be queried for.
     * @return the URL to use to query the GitHub server.
     *  */
    public static URL buildUrl(String githubSearchQuery) {
        //TODO: Fill in this method to build the proper GitHub query URL;
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /* This method returns the entire(все) result from the HTTP
     response(ответ).
     * @param url The URL to fetch(получить) the HTTP response from.
     * @return THe concepts of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url)
        throws IOException {
        HttpsURLConnection urlConnection =
                (HttpsURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
