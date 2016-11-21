package com.app.android.petoye;

import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Feed{
    String user_name,timestamp,img_description,like_count,author_id,comment_count,feed_id,imageOfPost,imageOfUser;

    public Feed(){}
    public Feed(String user_name,String timestamp,String img_description,
                String like_count,String comment_count,String author_id,String feed_id,String imageOfPost,String imageOfUser)
    {
        this.user_name=user_name;
        this.timestamp=timestamp;
        this.img_description=img_description;
        this.like_count=like_count;
        this.author_id=author_id;
        this.comment_count=comment_count;
        this.feed_id=feed_id;
        this.imageOfPost=imageOfPost;
        this.imageOfUser=imageOfUser;
    } //this constructor has no imagePOst
    public Feed(String user_name,String timestamp,String img_description,
                String like_count,String comment_count,String author_id,String feed_id,String imageOfUser)
    {
        this.user_name=user_name;
        this.timestamp=timestamp;
        this.img_description=img_description;
        this.like_count=like_count;
        this.author_id=author_id;
        this.comment_count=comment_count;
        this.feed_id=feed_id;
        this.imageOfUser=imageOfUser;
    }
    public Feed(JSONObject object)
    {
        try
        {
// this.sender_name = object.getJSONObject("user").getString("username");
            this.img_description = object.getString("message");
            this.user_name=object.getJSONObject("user").getString("username");
            if(object.getJSONObject("user").has("id"))
                this.author_id=object.getJSONObject("user").getString("id");
            this.like_count = object.getString("like_count");
            this.comment_count = object.getString("comment_count");
            this.timestamp = object.getString("created_at");
            this.feed_id=object.getString("id");
//if(object.getString("imageurl")!= "null")
            this.imageOfPost=object.getString("imageurl");
//  Log.i("TAG",object.getString("imageurl"));


//if(object.getJSONObject("user").getString("imageurl")!="null")
            if(object.getJSONObject("user").has("imageurl"))
                this.imageOfUser=object.getJSONObject("user").getString("imageurl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
// Comment.fromJson(jsonArray);
    public static ArrayList<Feed> fromJson(JSONArray jsonObjects) {
        ArrayList<Feed> feeds = new ArrayList<Feed>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                feeds.add(new Feed(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return feeds;
    }
}