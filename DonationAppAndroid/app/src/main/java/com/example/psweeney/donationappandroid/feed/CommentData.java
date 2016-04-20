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
 */
public class CommentData {
    public static String commentAuthorDisplayNameKey = "commentAuthorDisplayName";
    public static String commentTextKey = "commentText";

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

    public void addToBundle(Bundle bundle, int position){
        if(bundle == null){
            return;
        }
        bundle.putString(commentAuthorDisplayNameKey + Integer.toString(position), _authorDisplayName);
        bundle.putString(commentTextKey + Integer.toString(position), _commentText);
    }

    public static CommentData extractCommentDataFromBundle(Bundle bundle, int position){
        if(bundle == null){
            return null;
        }

        BitmapDrawable authorIcon = null;
        String authorDisplayName = "";
        String commentText = "";

        try{
            authorDisplayName = bundle.getString(commentAuthorDisplayNameKey + Integer.toString(position));
            commentText = bundle.getString(commentTextKey + Integer.toString(position));
        } catch (Exception e){
            return null;
        }

        return new CommentData(authorDisplayName, commentText);
    }
}
