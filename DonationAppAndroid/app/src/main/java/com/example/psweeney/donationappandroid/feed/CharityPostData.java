package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public class CharityPostData implements PostData {
    private Drawable _authorIcon;
    private String _authorDisplayName;
    private Drawable _bodyImage;
    private String _bodyText;
    private Calendar _postTime;
    private int _numLikes;
    private boolean _likedByUser;
    private ArrayList<CommentData> _comments;

    public CharityPostData(Drawable authorIcon, String authorDisplayName, Drawable bodyImage, String bodyText, Calendar postTime,
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

    public CharityPostData(Drawable authorIcon, String authorDisplayName, Drawable bodyImage, String bodyText, Calendar postTime){
        this(authorIcon, authorDisplayName, bodyImage, bodyText, postTime, 0, false, null);
    }

    public CharityPostData(Drawable authorIcon, String authorDisplayName, String bodyText, Calendar postTime){
        this(authorIcon, authorDisplayName, null, bodyText, postTime);
    }

    public CharityPostData(Drawable authorIcon, String authorDisplayName, Drawable bodyImage, Calendar postTime){
        this(authorIcon, authorDisplayName, bodyImage, null, postTime);
    }

    public CharityPostData(Drawable authorIcon, String authorDisplayName, Drawable bodyImage, String bodyText){
        this(authorIcon, authorDisplayName, bodyImage, bodyText, Calendar.getInstance());
    }

    public CharityPostData(Drawable authorIcon, String authorDisplayName, String bodyText){
        this(authorIcon, authorDisplayName, null, bodyText, Calendar.getInstance());
    }

    public CharityPostData(Drawable authorIcon, String authorDisplayName, Drawable bodyImage){
        this(authorIcon, authorDisplayName, bodyImage, null, Calendar.getInstance());
    }

    @Override
    public Drawable getAuthorIcon() {
        return _authorIcon;
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

    public Drawable getBodyImage(){
        return _bodyImage;
    }

    public String getDateDisplayString(){
        return FeedPostAdapter.buildPostTimeString(_postTime);
    }

    @Override
    public ArrayList<Object> getDataList() {
        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(_authorIcon);
        dataList.add(getDateDisplayString());
        dataList.add(_bodyImage);
        dataList.add(_bodyText);
        dataList.add(getTitleDisplayString());

        return dataList;
    }

    @Override
    public Map<Object, PostDataType> getDataTypeMap() {
        Map<Object, PostDataType> dataTypeMap = new HashMap<>();
        dataTypeMap.put(_authorIcon, PostDataType.DRAWABLE);
        dataTypeMap.put(getTitleDisplayString(), PostDataType.TEXT);
        dataTypeMap.put(_bodyImage, PostDataType.DRAWABLE);
        dataTypeMap.put(_bodyText, PostDataType.TEXT);
        dataTypeMap.put(getDateDisplayString(), PostDataType.TEXT);

        return dataTypeMap;
    }
}
