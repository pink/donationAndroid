package com.example.psweeney.donationappandroid.feed;

import android.os.Bundle;

import com.example.psweeney.donationappandroid.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by psweeney on 4/18/16.
 */
public abstract class PostData {
    public enum PostType{
        DONATION, CHARITY
    }

    public static String postIdentifierKey = "postIdentifier";
    public static int authorIconIdDefault = R.drawable.ic_photo_black_48dp;

    protected int _authorIconId = authorIconIdDefault;
    protected String _authorDisplayName = "";
    protected Calendar _postTime = Calendar.getInstance();
    protected int _numLikes = 0;
    protected boolean _likedByUser = false;
    protected List<CommentData> _comments = new ArrayList<>();

    public PostType getPostType(){ return PostType.DONATION; }

    public Integer getPostIdentifier(){
        return _authorDisplayName.hashCode() + _postTime.hashCode();
    }

    public int getAuthorIconId(){ return _authorIconId; }

    public String getAuthorDisplayName(){ return _authorDisplayName; }

    public Calendar getPostTime(){ return _postTime; }

    public int getNumLikes(){ return _numLikes; }

    public void setNumLikes(int newValue){ _numLikes = newValue; }

    public boolean likedByUser(){ return _likedByUser; }

    public void setLikedByUser(boolean newValue){ _likedByUser = newValue; }

    public List<CommentData> getComments(){ return _comments; }

    public void setComments(List<CommentData> newComments){
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

        bundle.putInt(postIdentifierKey, getPostIdentifier());
        return bundle;
    }

    public abstract String getTitleDisplayString();


}
