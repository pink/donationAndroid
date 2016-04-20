package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public abstract class PostData {
    public enum PostType{
        DONATION, CHARITY
    }

    public static String authorIconKey = "authorIcon";
    public static String authorDisplayNameKey = "authorDisplayName";
    public static String postTimeKey = "postTime";
    public static String numLikesKey = "numLikes";
    public static String likedByUserKey = "likedByUser";
    public static String numCommentsKey = "numComments";

    public abstract PostType getPostType();
    public abstract int getCount();
    public abstract BitmapDrawable getAuthorIcon();
    public abstract String getAuthorDisplayName();
    public abstract String getTitleDisplayString();
    public abstract Calendar getPostTime();
    public abstract String getDateDisplayString();
    public abstract int getNumLikes();
    public abstract void setNumLikes(int newValue);
    public abstract boolean likedByUser();
    public abstract void setLikedByUser(boolean newValue);
    public abstract ArrayList<CommentData> getComments();


}
