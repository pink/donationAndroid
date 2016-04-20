package com.example.psweeney.donationappandroid.feed;

import android.content.SharedPreferences;
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

    protected BitmapDrawable _authorIcon = null;
    protected String _authorDisplayName = "";
    protected Calendar _postTime = Calendar.getInstance();
    protected int _numLikes = 0;
    protected boolean _likedByUser = false;
    protected ArrayList<CommentData> _comments = new ArrayList<>();

    public PostType getPostType(){ return PostType.DONATION; }

    public BitmapDrawable getAuthorIcon(){ return _authorIcon; }

    public String getAuthorDisplayName(){ return _authorDisplayName; }

    public Calendar getPostTime(){ return _postTime; }

    public int getNumLikes(){ return _numLikes; }

    public void setNumLikes(int newValue){ _numLikes = newValue; }

    public boolean likedByUser(){ return _likedByUser; }

    public void setLikedByUser(boolean newValue){ _likedByUser = newValue; }

    public ArrayList<CommentData> getComments(){ return _comments; }

    public void setComments(ArrayList<CommentData> newComments){
        _comments = newComments;
    }

    public String getDateDisplayString(){
        String dateString = "";
        switch (_postTime.get(Calendar.MONTH)){
            case Calendar.JANUARY:
                dateString += "January";
                break;
            case Calendar.FEBRUARY:
                dateString += "February";
                break;
            case Calendar.MARCH:
                dateString += "March";
                break;
            case Calendar.APRIL:
                dateString += "April";
                break;
            case Calendar.MAY:
                dateString += "May";
                break;
            case Calendar.JUNE:
                dateString += "June";
                break;
            case Calendar.JULY:
                dateString += "July";
                break;
            case Calendar.AUGUST:
                dateString += "August";
                break;
            case Calendar.SEPTEMBER:
                dateString += "September";
                break;
            case Calendar.OCTOBER:
                dateString += "October";
                break;
            case Calendar.NOVEMBER:
                dateString += "November";
                break;
            case Calendar.DECEMBER:
                dateString += "December";
                break;
        }

        dateString += " " + _postTime.get(Calendar.DAY_OF_MONTH) + ", " + _postTime.get(Calendar.YEAR) + " at " +
                _postTime.get(Calendar.HOUR) + ":";

        if(_postTime.get(Calendar.MINUTE) < 10){
            dateString += "0";
        }
        dateString += _postTime.get(Calendar.MINUTE);

        if(_postTime.get(Calendar.AM_PM) == Calendar.AM){
            dateString += " AM";
        } else {
            dateString += " PM";
        }

        return dateString;
    }

    public Bundle convertToBundle(){
        Bundle bundle = new Bundle();
        if(bundle == null){
            return null;
        }

        bundle.putString("postType", getPostType().toString());
        bundle.putParcelable(authorIconKey, getAuthorIcon().getBitmap());
        bundle.putString(authorDisplayNameKey, getAuthorDisplayName());
        bundle.putSerializable(postTimeKey, getPostTime());
        bundle.putInt(numLikesKey, getNumLikes());
        bundle.putBoolean(likedByUserKey, likedByUser());

        ArrayList<CommentData> comments = getComments();


        if(comments == null){
            bundle.putInt(numCommentsKey, 0);
        } else {
            bundle.putInt(numCommentsKey, comments.size());
        }



        if(comments != null && comments.size() > 0){
            for(int i = 0; i < comments.size(); i++){
                comments.get(i).addToBundle(bundle, i);
            }
        }

        return bundle;
    }

    public abstract String getTitleDisplayString();


}
