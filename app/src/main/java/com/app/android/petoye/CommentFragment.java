package com.app.android.petoye;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CommentFragment extends DialogFragment {
    ArrayList<Comment> arrayOfComments;
    Context thisContext;
    ListView listView;
    CommentAdapter adapter;
    GlobalClass globalVariable;
    String feed_id;

    public CommentFragment() {
        // Required empty public constructor
    }
    public static CommentFragment newInstance(String feedID)
    {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString("feed_id",feedID);
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
        int dialogWidth = (int)(MainActivity.displayMetrics.widthPixels);
        int dialogHeight = (int)(MainActivity.displayMetrics.heightPixels *0.95);
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        thisContext=this.getContext();
        arrayOfComments = new ArrayList<Comment>();
        adapter = new CommentAdapter(thisContext, arrayOfComments);

       // globalVariable=(GlobalClass) getApplicationContext();
       /* JSONArray jsonArray = new JSONArray();
        ArrayList<Comment> comments = Comment.fromJson(jsonArray);

        adapter.addAll(comments);
        */
        //listView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle("Comments");
        listView = (ListView) view.findViewById(R.id.list_comments);
        String fid = getArguments().getString("feed_id");
        new DownloadComments().execute(fid);

    }
    public class DownloadComments extends AsyncTask<String, Void,Void>
    {
        @Override
        protected Void doInBackground(String... fid)
        {
            try
            {
                //take the feed id from the feeds data
                String url = "http://api.petoye.com/feeds/"+fid[0]+"/showcomment";
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Log.i("TAG", response.toString());
                                try {
                                    arrayOfComments=Comment.fromJson(response.getJSONArray("comments"));
                                    Log.i("TAG",String.valueOf(arrayOfComments.size()));
                                    adapter = new CommentAdapter(thisContext, arrayOfComments);
                                    listView.setAdapter(adapter);
                                }
                                catch (Exception e){}

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                NetworkResponse networkResponse = error.networkResponse;
                                if (networkResponse != null && networkResponse.statusCode == 422) {
                                    Log.i("TAG",networkResponse.headers.toString());

                                }
                            }
                        }){

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        //  headers.put("User-agent", "My useragent");
                        return headers;
                    }
                };

                // Access the RequestQueue through your singleton class.
                MySingleton.getInstance(thisContext).addToRequestQueue(jsObjRequest);
            }
            catch (Exception e){    }

            return  null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.i("TAG","in....");
        }
    }
}
