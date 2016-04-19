package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.Drawable;

/**
 * Created by psweeney on 4/19/16.
 */
public class CommentData {
    private Drawable _authorIcon;
    private String _authorDisplayName;
    private String _commentText;

    public CommentData(Drawable authorIcon, String authorDisplayName, String commentText){
        _authorIcon = authorIcon;
        _authorDisplayName = authorDisplayName;
        _commentText = commentText;
    }

    public Drawable getAuthorIcon() {
        return _authorIcon;
    }

    public String getAuthorDisplayName() {
        return _authorDisplayName;
    }

    public String getCommentText() {
        return _commentText;
    }
}
