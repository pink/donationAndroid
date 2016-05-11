package com.example.psweeney.donationappandroid.feed;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by psweeney on 4/19/16.
 *
 * Class used to store data for user comments. If implemented with a real, functional backend in mind,
 * this would have a reference to a user data object (which doesn't currently exist) rather than just
 * a user name string, but this rudimentary form works well enough for our purposes
 */
public class CommentData {
    private String _authorDisplayName = "";
    private String _commentText = "";

    public CommentData(String authorDisplayName, String commentText){
        _authorDisplayName = new String(authorDisplayName);
        _commentText = new String(commentText);
    }

    public String getAuthorDisplayName() {
        return _authorDisplayName;
    }

    public String getCommentText() {
        return _commentText;
    }
}
