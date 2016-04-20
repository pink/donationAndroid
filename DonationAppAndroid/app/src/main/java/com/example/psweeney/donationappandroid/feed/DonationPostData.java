package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public class DonationPostData extends PostData{
    public enum DonationPostDataFields{
        RECIPIENT_DISPLAY_NAME,  DONATION_AMOUNT_CENTS
    }

    public static String recipientDisplayNameKey = "recipientDisplayName";
    public static String donationAmountCentsKey = "donationAmountCents";

    private BitmapDrawable _authorIcon;
    private String _authorDisplayName;
    private String _recipientDisplayName;
    private Calendar _postTime;
    private int _donationAmountCents;
    private int _numLikes;
    private boolean _likedByUser;
    private ArrayList<CommentData> _comments;

    private static final String USER_POST_NAME = "You";

    public DonationPostData(BitmapDrawable authorIcon, String authorDisplayName, String recipientDisplayName, Calendar postTime, int donationAmountCents,
                            int numLikes, boolean likedByUser, ArrayList<CommentData> comments){
        _authorIcon = authorIcon;
        if(authorDisplayName == null){
            _authorDisplayName = new String(USER_POST_NAME);
        } else {
            _authorDisplayName = new String(authorDisplayName);
        }

        _recipientDisplayName = new String(recipientDisplayName);
        _postTime = (Calendar) postTime.clone();
        _donationAmountCents = donationAmountCents;
        _numLikes = numLikes;
        _likedByUser = likedByUser;
        if(comments == null){
            _comments = new ArrayList<>();
        } else {
            _comments = new ArrayList<>(comments);
        }
    }

    public DonationPostData(BitmapDrawable authorIcon, String authorDisplayName, String recipientDisplayName, Calendar postTime, int donationAmountCents){
        this(authorIcon, authorDisplayName, recipientDisplayName, postTime, donationAmountCents, 0, false, null);
    }

    public DonationPostData(BitmapDrawable authorIcon, String authorDisplayName, String recipientDisplayName, int donationAmountCents){
        this(authorIcon, authorDisplayName, recipientDisplayName, Calendar.getInstance(), donationAmountCents);
    }

    public DonationPostData(DonationPostData other){
        this(other._authorIcon, other._authorDisplayName, other._recipientDisplayName, other._postTime, other._donationAmountCents);
    }

    @Override
    public PostType getPostType() {
        return PostType.DONATION;
    }

    public BitmapDrawable getAuthorIcon(){
        return _authorIcon;
    }

    @Override
    public String getAuthorDisplayName() {
        return _authorDisplayName;
    }

    public String getRecipientDisplayName(){
        return _recipientDisplayName;
    }

    public int getDonationAmountCents(){
        return _donationAmountCents;
    }

    @Override
    public Calendar getPostTime() {
        return _postTime;
    }

    @Override
    public int getNumLikes() {
        return _numLikes;
    }

    @Override
    public void setNumLikes(int newValue) {
        _numLikes = Math.max(0, newValue);
    }

    @Override
    public boolean likedByUser() {
        return _likedByUser;
    }

    @Override
    public void setLikedByUser(boolean newValue) {
        _likedByUser = newValue;
    }

    @Override
    public ArrayList<CommentData> getComments() {
        return _comments;
    }

    @Override
    public int getCount() {
        return 3;
    }

    private String getDonationAmountDisplayString(){
        int dollarNum = _donationAmountCents / 100, centsNum = _donationAmountCents % 100;
        String ret = "$" + dollarNum + ".";

        if(centsNum < 10){
            ret += "0";
        }
        ret += centsNum;
        return ret;
    }

    public String getTitleDisplayString(){
        return _authorDisplayName + " donated " + getDonationAmountDisplayString() + " to " + _recipientDisplayName + ".";
    }

    public String getDateDisplayString(){
        return FeedPostAdapter.buildPostTimeString(_postTime);
    }
}
