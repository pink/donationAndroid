package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

import java.util.List;

/**
 * Created by psweeney on 4/19/16.
 */
public class CommentAdapter extends ArrayAdapter {
    private List<CommentData> _comments;
    public CommentAdapter(Context context, int resource, List<CommentData> comments) {
        super(context, resource, comments);
        _comments = comments;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        CommentData curr = _comments.get(position);

        View rowView = inflater.inflate(R.layout.feed_comment, parent, false);

        ImageView imageViewAuthorIcon = (ImageView) rowView.findViewById(R.id.imageViewCommentAuthorIcon);
        imageViewAuthorIcon.setImageDrawable(curr.getAuthorIcon());

        TextView textViewAuthorName = (TextView) rowView.findViewById(R.id.textViewCommentAuthor);
        textViewAuthorName.setText(curr.getAuthorDisplayName());

        TextView textViewCommentBody = (TextView) rowView.findViewById(R.id.textViewCommentBody);
        textViewCommentBody.setText(curr.getCommentText());

        return rowView;
    }
}
