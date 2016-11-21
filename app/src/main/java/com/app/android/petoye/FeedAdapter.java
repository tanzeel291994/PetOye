package com.app.android.petoye;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.ocpsoft.pretty.time.PrettyTime;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FeedAdapter extends ArrayAdapter<Feed> {

    Context thisActivityContext;
    PrettyTime prettyTime;

    SimpleDateFormat format;
    private class ViewHolder
    {
        CircularImageView user_img;
        TextView username,timestamp,img_description,like_comment_count;
        NetworkImageView feed_img;
    }

    public FeedAdapter(Context context, ArrayList<Feed> feeds) {
        super(context, R.layout.item_feed, feeds);
        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        thisActivityContext=context;
        prettyTime = new PrettyTime();
       }

    @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       final Feed feed = (Feed) getItem(position);
        ImageLoader imageLoader;
       // Check if an existing view is being reused, otherwise inflate the view
      final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
           // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
           LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_feed, parent, false);
           //if(feed.imageOfPost!="null")
             viewHolder.feed_img=(NetworkImageView)convertView.findViewById(R.id.feed_img);
            //Log.i("TAG","when feed image is not null so image view instantiating");

           viewHolder.username=(TextView)convertView.findViewById(R.id.username);
            viewHolder.timestamp=(TextView)convertView.findViewById(R.id.timestamp);
           viewHolder.img_description=(TextView)convertView.findViewById(R.id.img_description);
           viewHolder.like_comment_count=(TextView)convertView.findViewById(R.id.likes_comments_count);
           viewHolder.user_img=(com.github.siyamed.shapeimageview.CircularImageView)convertView.findViewById(R.id.img_user);

       //  viewHolder.user_img=(NetworkImageView)convertView.findViewById(R.id.user_img);
         // Cache the viewHolder object inside the fresh view
          convertView.setTag(viewHolder);
          } else {
            // View is being recycled, retrieve the viewHolder object from tag
      viewHolder = (ViewHolder) convertView.getTag();
           }
        // Populate the data into the template view using the data object
       viewHolder.username.setText(feed.user_name);
        Date date;
        try {
            date = format.parse(feed.timestamp.substring(0,19)+'Z');
            viewHolder.timestamp.setText((prettyTime.format(new Date(date.getTime()))));
          // viewHolder.timestamp.setText(createdAt);
           } catch (ParseException e) {
            viewHolder.timestamp.setText("NA");
            }


     viewHolder.img_description.setText(feed.img_description);
        viewHolder.like_comment_count.setText(feed.like_count+" Likes "+ feed.comment_count+" Comments");

     imageLoader = MySingleton.getInstance(thisActivityContext).getImageLoader();

      if(feed.imageOfPost!="null")
      {
          viewHolder.feed_img.setImageUrl(feed.imageOfPost, imageLoader);
      }

      if(feed.imageOfUser!="null")
          Picasso.with(thisActivityContext).load(feed.imageOfUser).into(viewHolder.user_img);
       //viewHolder.user_img.setImageUrl(feed.imageOfUser, imageLoader);
        final ImageButton btn_like_feed = (ImageButton) convertView.findViewById(R.id.btnLike_feed);
        btn_like_feed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//feed.like_count=feed.like_count+1;
               // viewHolder.like_comment_count.setText(String.valueOf(Integer.parseInt(feed.like_count)+1)+" Likes "+ feed.comment_count+" Comments");
               btn_like_feed.setColorFilter(Color.parseColor("#59c3cc"));   //mediunTurquise color
                //new FollowedFragment.LikeFeed().execute(feed.feed_id);
            }
        });
        final ViewGroup parentTemp=parent;
        ImageButton btn_comment_feed = (ImageButton) convertView.findViewById(R.id.btn_comments_feed);
        btn_comment_feed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Context context = parentTemp.getContext();
                FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                CommentFragment commentFragment = CommentFragment.newInstance(feed.feed_id);
                commentFragment.show(fm,"fragment_comment");
                // Intent i = new Intent(FollowedFragment.thisActivityContext, CommentScreen.class);
               // i.putExtra("feed_id",feed.feed_id);
               // FollowedFragment.thisActivityContext.startActivity(i);
            }
        });
        ImageButton report_button=(ImageButton)convertView.findViewById(R.id.report_button);
        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i=new Intent(thisActivityContext,PostOptions.class);
                //thisActivityContext.startActivity(i);
            }
        });
        return convertView;
      }

    }


