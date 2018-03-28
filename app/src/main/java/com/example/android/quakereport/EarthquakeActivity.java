/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.content.AsyncTaskLoader;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Quake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    public ListView earthquakeListView;
    private static final String USGS_REQUEST_URL ="https://earthquake.usgs.gov/fdsnws/event/1/query";
    private TextView mEmptyStateTextView;
    private QuakeAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
      /*  ArrayList<Quake> earthquakes = new ArrayList<Quake>();
        earthquakes.add( new Quake("7.2","San Francisco","Feb 2,2016"));

        earthquakes.add( new Quake("6.1","London","july 20,2015"));
        earthquakes.add( new Quake("3.9","Tokyo","Feb 2,2016"));

        earthquakes.add( new Quake("6.1","Mexico City","july 2,2017"));
        earthquakes.add( new Quake("6.1","Rio de Janeiro","july 20,2015"));
        earthquakes.add( new Quake("3.9","San Francisco","Feb 2,2016"));

        earthquakes.add( new Quake("6.1","paris","july 2,2017")); */
      // Create a fake list of earthquakes.
        //ArrayList<Quake> earthquakes = QueryUtils.extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);
        mAdapter = new QuakeAdapter(this, new ArrayList<Quake>());

        // Create a new {@link ArrayAdapter} of earthquakes
       // final QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

       // LinearLayout setup =  (LinearLayout) findViewById(R.id.clickme);

       earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                   // Find the current earthquake that was clicked on
                  // -Earthquake currentEarthquake = adapter.getItem(position);
                  // Earthquake currentEarthquake = mAdapter.getItem(position);

                   // Convert the String URL into a URI object (to pass into the Intent constructor)
                   Quake word = mAdapter.getItem(position);
                   String url = word.gettUrl();
                   // Create a new intent to view the earthquake URI
                   Intent ik = new Intent(Intent.ACTION_VIEW);
                   ik.setData(Uri.parse(url));
                   // Send the intent to launch a new activity
                   startActivity(ik);
               }

       });
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

        // Start the AsyncTask to fetch the earthquake data
        //EarthQuakeAysncTask task = new EarthQuakeAysncTask();
                //task.execute(USGS_REQUEST_URL);


    /**
     +     * {@link AsyncTask} to perform the network request on a background thread, and then
     +     * update the UI with the list of earthquakes in the response.
     +     *
     +     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     +     * an output type. Our task will take a String URL, and return an Earthquake. We won't do
     +     * progress updates, so the second generic is just Void.
     +     *
     +     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     +     * The doInBackground() method runs on a background thread, so it can run long-running code
     +     * (like network activity), without interfering with the responsiveness of the app.
     +     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
     +     * UI thread, so it can use the produced data to update the UI.
     +     */

      /*  private class EarthQuakeAysncTask extends AsyncTask<String,Void,List<Quake>> {


        @Override
        protected List<Quake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Quake> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;
        }
*/
    //@Override
   /*public Loader<List<Quake>> onCreateLoader(int i, Bundle bundle) {
              // Create a new loader for the given URL
                       return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }*/
    @Override
    public Loader<List<Quake>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);

        return new EarthquakeLoader(this, uriBuilder.toString());
    }
       @Override
    public void onLoadFinished(Loader<List<Quake>> loader, List<Quake> earthquakes) {
           View loadingIndicator = findViewById(R.id.loading_indicator);
           loadingIndicator.setVisibility(View.GONE);

                // Clear the adapter of previous earthquake data
           mEmptyStateTextView.setText(R.string.no_earthquakes);

           // Clear the adapter of previous earthquake data
           mAdapter.clear();

           // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
           // data set. This will trigger the ListView to update.
           if (earthquakes != null && !earthquakes.isEmpty()) {
               // mAdapter.addAll(earthquakes);
           }
                        mAdapter.clear();

                        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
                                // data set. This will trigger the ListView to update.
                                        if (earthquakes != null && !earthquakes.isEmpty()) {
                        mAdapter.addAll(earthquakes);
        }
            }
        /**
         * +         * This method runs on the main UI thread after the background work has been
         * +         * completed. This method receives as input, the return value from the doInBackground()
         * +         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * +         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * +         * which will trigger the ListView to re-populate its list items.
         * +
         */
       /* @Override
        protected void onPostExecute(List<Quake> result) {
            // If there is no result, do nothing.
            mAdapter.clear();
            if (result != null && !result.isEmpty()) {
                mAdapter.addAll(result);
            }*/
        //}
    @Override
    public void onLoaderReset(Loader<List<Quake>> loader) {
                // Loader reset, so we can clear out our existing data.
                        mAdapter.clear();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    }









