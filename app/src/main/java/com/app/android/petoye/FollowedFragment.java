package com.app.android.petoye;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FollowedFragment extends Fragment {



View progressOverlay;
    ListView list_followed_feeds;
    ArrayAdapter adapter;
    ArrayList<Feed> arrayOfFeeds;
    static Context thisActivityContext;
    GlobalClass globalClass;
    String uid;

    public FollowedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.feeds_list, container, false);
progressOverlay= rootView.findViewById(R.id.progress_overlay);
        arrayOfFeeds = new ArrayList<Feed>();
        Log.i("tag","in fedds ");
        //globalClass=(GlobalClass)getContext();
        //uid=globalClass.getUid();
        thisActivityContext = getContext();

        adapter = new FeedAdapter(this.getActivity(), arrayOfFeeds);

        list_followed_feeds = (ListView) rootView.findViewById(R.id.list_feeds);
        list_followed_feeds.setAdapter(adapter);

        new DownloadFollowedFeeds().execute();
        return rootView;
    }
    public class DownloadFollowedFeeds extends AsyncTask<Void, Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                //String url = "http://api.petoye.com/conversations/"+globalVariable.getUid()+"/all";
                Log.i("tag","in background");
                Util.animateView(progressOverlay, View.VISIBLE, 0.4f, 200);
                String url = "http://api.petoye.com//feeds/1/followedfeeds";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Log.i("TAG", response.toString());
                                try {

                                    arrayOfFeeds = Feed.fromJson(response.getJSONArray("feeds"));
                                    adapter = new FeedAdapter(thisActivityContext,arrayOfFeeds);
                                    list_followed_feeds.setAdapter(adapter);
                                    Util.animateView(progressOverlay, View.GONE, 0, 200);
                                } catch (Exception e)
                                {
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                NetworkResponse networkResponse = error.networkResponse;
                                if (networkResponse != null && networkResponse.statusCode == 422) {
                                    Log.i("TAG", networkResponse.headers.toString());

                                }
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        //  headers.put("User-agent", "My useragent");
                        return headers;
                    }

                };
                // Access the RequestQueue through your singleton class.
                MySingleton.getInstance(thisActivityContext).addToRequestQueue(jsObjRequest);

            } catch (Exception e) {
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.i("TAG", "in....");
        }
    }
}