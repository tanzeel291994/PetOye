package com.app.android.petoye;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 11/21/2016.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    Context thisContext;
    // View lookup cache
    private static class ViewHolder {
        CircularImageView img;
        TextView name;
        TextView msg;
    }

    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        super(context, R.layout.item_comment, comments);
        thisContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Comment comment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_comment, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.comment_username);
            viewHolder.msg = (TextView) convertView.findViewById(R.id.comment_msg);
            viewHolder.img = (com.github.siyamed.shapeimageview.CircularImageView) convertView.findViewById(R.id.img_user_comment);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if( comment.img_user != "null")
        {  Picasso.with(thisContext).load(comment.img_user).into(viewHolder.img);
            Log.i("TAG",comment.img_user);}
        // Populate the data into the template view using the data object
        viewHolder.name.setText(comment.comment_username);
        viewHolder.msg.setText(comment.comment_msg);
        // Return the completed view to render on screen
        return convertView;
    }
}
