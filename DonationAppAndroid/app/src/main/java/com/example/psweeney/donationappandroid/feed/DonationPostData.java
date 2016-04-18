package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public class DonationPostData implements PostData{
    private Drawable _authorIcon;
    private String _authorDisplayName;
    private String _recipientDisplayName;
    private Calendar _postTime;
    private int _donationAmountCents;

    private static final String USER_POST_NAME = "You";

    public DonationPostData(Drawable authorIcon, String authorDisplayName, String recipientDisplayName, Calendar postTime, int donationAmountCents){
        _authorIcon = authorIcon;
        if(authorDisplayName == null){
            _authorDisplayName = USER_POST_NAME;
        } else {
            _authorDisplayName = authorDisplayName;
        }

        _recipientDisplayName = recipientDisplayName;
        _postTime = postTime;
        _donationAmountCents = donationAmountCents;
    }

    public DonationPostData(Drawable authorIcon, String authorDisplayName, String recipientDisplayName, int donationAmountCents){
        this(authorIcon, authorDisplayName, recipientDisplayName, Calendar.getInstance(), donationAmountCents);
    }

    public DonationPostData(DonationPostData other){
        this(other._authorIcon, other._authorDisplayName, other._recipientDisplayName, other._postTime, other._donationAmountCents);
    }

    public Drawable getAuthorIcon(){
        return _authorIcon;
    }

    @Override
    public Calendar getPostTime() {
        return _postTime;
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

    @Override
    public ArrayList<Object> getDataList() {
        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(_authorIcon);
        dataList.add(getDateDisplayString());
        dataList.add(getTitleDisplayString());

        return dataList;
    }

    @Override
    public Map<Object, PostDataType> getDataTypeMap() {
        Map<Object, PostDataType> dataTypeMap = new HashMap<>();
        dataTypeMap.put(_authorIcon, PostDataType.DRAWABLE);
        dataTypeMap.put(getDateDisplayString(), PostDataType.TEXT);
        dataTypeMap.put(getTitleDisplayString(), PostDataType.TEXT);
        return dataTypeMap;
    }
}
