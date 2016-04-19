package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public interface PostData {
    public enum PostDataType {
        TEXT, DRAWABLE
    }

    public int getCount();
    public Drawable getAuthorIcon();
    public String getTitleDisplayString();
    public Calendar getPostTime();
    public String getDateDisplayString();
    public ArrayList<Object> getDataList();
    public Map<Object, PostDataType> getDataTypeMap();
    public int getNumLikes();
    public void setNumLikes(int newValue);
    public boolean likedByUser();
    public void setLikedByUser(boolean newValue);
    public ArrayList<CommentData> getComments();
}
