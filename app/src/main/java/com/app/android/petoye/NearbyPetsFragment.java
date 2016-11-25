package com.app.android.petoye;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Nearby Pets Fragment
 */
public class NearbyPetsFragment extends Fragment {

    ListView list_nearby_pets_feeds;
    ArrayAdapter adapter;
    ArrayList<Feed> arrayOfFeeds;

    public NearbyPetsFragment() {
    }

    public NearbyPetsFragment newInstance() {
        NearbyPetsFragment fragment = new NearbyPetsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feeds_list, container, false);

        arrayOfFeeds = new ArrayList<Feed>();

        adapter = new FeedAdapter(this.getActivity(), arrayOfFeeds);



        list_nearby_pets_feeds = (ListView) rootView.findViewById(R.id.list_feeds);
        list_nearby_pets_feeds.setAdapter(adapter);

        return rootView;
    }
}
