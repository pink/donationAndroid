package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by psweeney on 4/19/16.
 *
 * LinearLayout extension used to store a CommentData object
 *
 */
public class CommentContainer extends LinearLayout{
    private CommentData _commentData;
    public CommentContainer(Context context) {
        super(context);
    }

    public CommentContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CommentData getCommentData(){
        return _commentData;
    }

    public void setCommentData(CommentData commentData) {
        _commentData = commentData;
    }
}
