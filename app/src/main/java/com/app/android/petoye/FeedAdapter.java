package com.app.android.petoye;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedAdapter extends ArrayAdapter<Feed> {

    Context thisActivityContext;

    // View lookup cache
    private class ViewHolder {
        ImageView feed_img;
        TextView username, timestamp, img_description, like_comment_count;
    }

    public FeedAdapter(Context context, ArrayList<Feed> feeds) {
        super(context, R.layout.item_feed, feeds);
        thisActivityContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Feed feed = (Feed) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_feed, parent, false);

            viewHolder.feed_img = (ImageView) convertView.findViewById(R.id.feed_img);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);
            viewHolder.img_description = (TextView) convertView.findViewById(R.id.img_description);
            viewHolder.like_comment_count = (TextView) convertView.findViewById(R.id.likes_comments_count);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.username.setText(feed.user_name);
        viewHolder.timestamp.setText(feed.timestamp);
        viewHolder.img_description.setText(feed.img_description);
        viewHolder.like_comment_count.setText(feed.like_count + " Likes " + feed.comment_count + " Comments");
        //viewHolder.feed_img.setImageBitmap();
        Button btn_like_feed = (Button) convertView.findViewById(R.id.btnLike_feed);
        btn_like_feed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //feed.like_count=feed.like_count+1;
                viewHolder.like_comment_count.setText(String.valueOf(Integer.parseInt(feed.like_count) + 1) + " Likes " + feed.comment_count + " Comments");
               // new FollowedFragment.LikeFeed().execute(feed.feed_id);
            }
        });
        Button btn_comment_feed = (Button) convertView.findViewById(R.id.btn_comments_feed);
        btn_comment_feed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Intent i = new Intent(FollowedFragment.thisActivityContext, CommentScreen.class);
               // i.putExtra("feed_id", feed.feed_id);
                //FollowedFragment.thisActivityContext.startActivity(i);
            }
        });
        ImageButton report_button = (ImageButton) convertView.findViewById(R.id.report_button);
        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent i = new Intent(thisActivityContext, PostOptions.class);
                //thisActivityContext.startActivity(i);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}

