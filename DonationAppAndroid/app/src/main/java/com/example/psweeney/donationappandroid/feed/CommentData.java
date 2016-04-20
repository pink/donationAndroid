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
    private BitmapDrawable _authorIcon;
    private String _authorDisplayName;
    private String _commentText;

    public CommentData(BitmapDrawable authorIcon, String authorDisplayName, String commentText){
        _authorIcon = authorIcon;
        _authorDisplayName = new String(authorDisplayName);
        _commentText = new String(commentText);
    }

    public BitmapDrawable getAuthorIcon() {
        return _authorIcon;
    }

    public String getAuthorDisplayName() {
        return _authorDisplayName;
    }

    public String getCommentText() {
        return _commentText;
    }

    public static String getAuthorIconBundleKey(int commentNum){
        return "COMMENT_AUTHOR_ICON_" + commentNum;
    }

    public static String getAuthorDisplayNameBundleKey(int commentNum){
        return "COMMENT_AUTHOR_DISPLAY_NAME_" + commentNum;
    }

    public static String getCommentTextBundleKey(int commentNum){
        return "COMMENT_TEXT_" + commentNum;
    }

    public static ArrayList<CommentData> extractCommentsFromBundle(Bundle b){
        ArrayList<CommentData> comments = new ArrayList<>();
        if(b == null || !b.containsKey(PostData.numCommentsKey)){
            return comments;
        }

        int numComments = b.getInt(PostData.numCommentsKey);
        if(numComments <= 0){
            return comments;
        }

        for(int i = 0; i < numComments; i++){
            BitmapDrawable authorIcon = b.getParcelable(CommentData.getAuthorIconBundleKey(i));
            String authorDisplayName = b.getString(CommentData.getAuthorDisplayNameBundleKey(i));
            String commentText = b.getString(CommentData.getCommentTextBundleKey(i));
            comments.add(new CommentData(authorIcon, authorDisplayName, commentText));
        }

        return comments;
    }
}
