package com.app.android.petoye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FollowedFragment extends Fragment {

    ListView list_followed_feeds;
    ArrayAdapter adapter;
    ArrayList<Feed> arrayOfFeeds;
    static Context thisActivityContext;
    GlobalClass globalClass;
    String uid;

    public FollowedFragment() {
    }

    public static FollowedFragment newInstance() {
        FollowedFragment fragment = new FollowedFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.feeds_list, container, false);

        arrayOfFeeds = new ArrayList<Feed>();
        //globalClass=(GlobalClass)getContext();
        //uid=globalClass.getUid();
        thisActivityContext = getContext();

        adapter = new FeedAdapter(this.getActivity(), arrayOfFeeds);

        list_followed_feeds = (ListView) rootView.findViewById(R.id.list_feeds);
        list_followed_feeds.setAdapter(adapter);

        // new DownloadFollowedFeeds().execute();
        return rootView;
    }
}