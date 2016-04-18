package com.example.psweeney.donationappandroid.feed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public class DonationPostData implements PostData{
    private int _authorIconId;
    private String _authorDisplayName;
    private String _recipientDisplayName;
    private Calendar _postTime;
    private int _donationAmountCents;

    private static final String USER_POST_NAME = "You";

    public DonationPostData(int authorIconId, String authorDisplayName, String recipientDisplayName, int donationAmountCents){
        _authorIconId = authorIconId;
        if(authorDisplayName == null){
            _authorDisplayName = USER_POST_NAME;
        } else {
            _authorDisplayName = authorDisplayName;
        }

        _recipientDisplayName = recipientDisplayName;
        _postTime = Calendar.getInstance();
        _donationAmountCents = donationAmountCents;
    }

    public int getAuthorIconId(){
        return _authorIconId;
    }

    @Override
    public int getCount() {
        return 3;
    }

    private String getDonationAmountDisplayString(){
        int dollarNum = _donationAmountCents / 100, centsNum = _donationAmountCents % 100;
        String ret = "$" + dollarNum + "." + centsNum;
        if(centsNum < 10){
            ret += "0";
        }
        return ret;
    }

    public String buildTopString(){
        return _authorDisplayName + " donated " + getDonationAmountDisplayString() + " to " + _recipientDisplayName + ".";
    }

    private String getDateDisplayString(){
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

        dateString += " " + _postTime.get(Calendar.DAY_OF_MONTH) + " at ";
        dateString += _postTime.get(Calendar.HOUR) + ":" + _postTime.get(Calendar.MINUTE);
        if(_postTime.get(Calendar.AM_PM) == Calendar.AM){
            dateString += " AM";
        } else {
            dateString += " PM";
        }

        return dateString;
    }

    public String buildBottomString(){
        return getDateDisplayString();
    }

    @Override
    public ArrayList<Object> getDataList() {
        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(_authorIconId);
        dataList.add(buildBottomString());
        dataList.add(buildTopString());

        return dataList;
    }

    @Override
    public Map<Object, PostDataType> getDataTypeMap() {
        Map<Object, PostDataType> dataTypeMap = new HashMap<>();
        dataTypeMap.put(_authorIconId, PostDataType.DRAWABLE_ID);
        dataTypeMap.put(buildBottomString(), PostDataType.TEXT);
        dataTypeMap.put(buildTopString(), PostDataType.TEXT);
        return dataTypeMap;
    }
}
