package com.foursquare.takehome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvRecyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRecyclerView = findViewById(R.id.rvRecyclerView);
        personAdapter = new PersonAdapter();

        //TODO hook up your adapter and any additional logic here

        new VenueFetcher(this).execute();
    }

    /**
     * Fakes a data fetch and parses json from assets/people.json
     */
    private static class VenueFetcher extends AsyncTask<Void, Void, Venue> {
        private final WeakReference<MainActivity> activityWeakReference;

        public VenueFetcher(MainActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected Venue doInBackground(Void... params) {
            return activityWeakReference != null ? VenueStore.get().getVenue(activityWeakReference.get()) : null;
        }

        @Override
        protected void onPostExecute(Venue venue) {
            if (activityWeakReference == null || venue == null) {
                return;
            }

            MainActivity mainActivity = activityWeakReference.get();

            //TODO use the venue object to populate your recyclerview
        }
    }
}
