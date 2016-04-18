package com.example.psweeney.donationappandroid.feed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by psweeney on 4/18/16.
 */
public interface PostData {
    public enum PostDataType {
        TEXT, DRAWABLE_ID
    }

    public int getCount();
    public int getAuthorIconId();
    public String getTitleDisplayString();
    public String getDateDisplayString();
    public ArrayList<Object> getDataList();
    public Map<Object, PostDataType> getDataTypeMap();
}
