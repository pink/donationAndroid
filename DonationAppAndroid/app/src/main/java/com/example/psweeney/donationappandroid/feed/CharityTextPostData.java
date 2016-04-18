package com.example.psweeney.donationappandroid.feed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public class CharityTextPostData implements PostData {
    private int _authorIconId;
    private String _authorDisplayName;
    private String _bodyText;
    private Calendar _postTime;

    public CharityTextPostData(int authorIconId, String authorDisplayName, String bodyText, Calendar postTime){
        _authorIconId = authorIconId;
        _authorDisplayName = authorDisplayName;
        _bodyText = bodyText;
        _postTime = postTime;
    }

    public CharityTextPostData(int authorIconId, String authorDisplayName, String bodyText){
        this(authorIconId, authorDisplayName, bodyText, Calendar.getInstance());
    }

    @Override
    public int getAuthorIconId() {
        return _authorIconId;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public String getTitleDisplayString(){
        return _authorDisplayName + " posted a new update:";
    }

    public String getBodyText(){
        return _bodyText;
    }

    public String getDateDisplayString(){
        return FeedPostAdapter.buildPostTimeString(_postTime);
    }

    @Override
    public ArrayList<Object> getDataList() {
        ArrayList<Object> dataList = new ArrayList<>();
        dataList.add(_authorIconId);
        dataList.add(getDateDisplayString());
        dataList.add(_bodyText);
        dataList.add(getTitleDisplayString());

        return dataList;
    }

    @Override
    public Map<Object, PostDataType> getDataTypeMap() {
        Map<Object, PostDataType> dataTypeMap = new HashMap<>();
        dataTypeMap.put(_authorIconId, PostDataType.DRAWABLE_ID);
        dataTypeMap.put(getTitleDisplayString(), PostDataType.TEXT);
        dataTypeMap.put(_bodyText, PostDataType.TEXT);
        dataTypeMap.put(getDateDisplayString(), PostDataType.TEXT);

        return dataTypeMap;
    }
}
