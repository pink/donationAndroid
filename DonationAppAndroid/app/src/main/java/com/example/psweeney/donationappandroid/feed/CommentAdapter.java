package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by psweeney on 4/19/16.
 */
public class CommentAdapter extends ArrayAdapter {
    private PostData _data;
    public CommentAdapter(Context context, int resource, PostData data) {
        super(context, resource, data.getComments());
        _data = data;
    }

    @Override
    public int getCount() {
        return _data.getComments().size() + 3;
    }

    public List<CommentData> getComments(){
        if(_data.getComments() == null){
            _data.setComments(new ArrayList<CommentData>());
        }
        return _data.getComments();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        List<CommentData> comments = getComments();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch (position){
            case 0:
                PostContainer mainPost = null;
                if(_data instanceof DonationPostData){
                    mainPost = (PostContainer) inflater.inflate(R.layout.feed_post_donation, parent, false);
                } else {
                    mainPost = (PostContainer) inflater.inflate(R.layout.feed_post_charity, parent, false);
                }

                if(mainPost != null){
                    mainPost.setData(_data);
                    mainPost.updateViews();
                }
                return mainPost;
            case 1:
                return inflater.inflate(R.layout.comment_bar, parent, false);
        }

        if(position == getCount() - 1){
            View addComment = inflater.inflate(R.layout.add_comment, parent, false);
            return addComment;
        }

        CommentData curr = comments.get(position - 2);

        View rowView = inflater.inflate(R.layout.feed_comment, parent, false);

        TextView textViewAuthorName = (TextView) rowView.findViewById(R.id.textViewCommentAuthor);
        textViewAuthorName.setText(curr.getAuthorDisplayName());

        TextView textViewCommentBody = (TextView) rowView.findViewById(R.id.textViewCommentBody);
        textViewCommentBody.setText(curr.getCommentText());

        if(position == getCount() - 2){
            View bottomDivider = rowView.findViewById(R.id.commentBottomDivider);
            if(bottomDivider != null){
                bottomDivider.setVisibility(View.GONE);
            }
        }

        return rowView;
    }
}
