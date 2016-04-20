package com.example.psweeney.donationappandroid.feed;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public class CharityPostData extends PostData {
    public enum CharityPostDataFields{
        BODY_TEXT, BODY_IMAGE
    }

    public static String bodyTextKey = "bodyText";
    public static String bodyImageKey = "bodyImage";

    private BitmapDrawable _authorIcon;
    private String _authorDisplayName;
    private BitmapDrawable _bodyImage;
    private Calendar _postTime;
    private String _bodyText;
    private int _numLikes;
    private boolean _likedByUser;
    private ArrayList<CommentData> _comments;

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, BitmapDrawable bodyImage, String bodyText, Calendar postTime,
                           int numLikes, boolean likedByUser, ArrayList<CommentData> comments){
        _authorIcon = authorIcon;
        _authorDisplayName = authorDisplayName;
        _bodyImage = bodyImage;
        _bodyText = bodyText;
        _postTime = postTime;
        _numLikes = numLikes;
        _likedByUser = likedByUser;
        if(comments == null){
            _comments = new ArrayList<>();
        } else {
            _comments = comments;
        }
    }

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, BitmapDrawable bodyImage, String bodyText, Calendar postTime){
        this(authorIcon, authorDisplayName, bodyImage, bodyText, postTime, 0, false, null);
    }

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, String bodyText, Calendar postTime){
        this(authorIcon, authorDisplayName, null, bodyText, postTime);
    }

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, BitmapDrawable bodyImage, Calendar postTime){
        this(authorIcon, authorDisplayName, bodyImage, null, postTime);
    }

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, BitmapDrawable bodyImage, String bodyText){
        this(authorIcon, authorDisplayName, bodyImage, bodyText, Calendar.getInstance());
    }

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, String bodyText){
        this(authorIcon, authorDisplayName, null, bodyText, Calendar.getInstance());
    }

    public CharityPostData(BitmapDrawable authorIcon, String authorDisplayName, BitmapDrawable bodyImage){
        this(authorIcon, authorDisplayName, bodyImage, null, Calendar.getInstance());
    }

    @Override
    public PostType getPostType() {
        return PostType.CHARITY;
    }

    @Override
    public BitmapDrawable getAuthorIcon() {
        return _authorIcon;
    }

    @Override
    public String getAuthorDisplayName() {
        return _authorDisplayName;
    }

    @Override
    public Calendar getPostTime() {
        return _postTime;
    }

    @Override
    public int getCount() {
        int total = 3;
        if(_bodyImage != null){
            total++;
        }

        if(_bodyText != null){
            total++;
        }

        return total;
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

    public String getTitleDisplayString(){
        return _authorDisplayName + " posted a new update:";
    }

    public String getBodyText(){
        return _bodyText;
    }

    public BitmapDrawable getBodyImage(){
        return _bodyImage;
    }

    public String getDateDisplayString(){
        return FeedPostAdapter.buildPostTimeString(_postTime);
    }
}
