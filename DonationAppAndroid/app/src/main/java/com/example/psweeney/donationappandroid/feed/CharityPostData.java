package com.example.psweeney.donationappandroid.feed;

import android.graphics.Bitmap;
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
public class CharityPostData extends PostData {
    public enum CharityPostDataFields{
        BODY_TEXT, BODY_IMAGE
    }

    public static String bodyTextKey = "bodyText";
    public static String bodyImageKey = "bodyImage";

    private BitmapDrawable _bodyImage;
    private String _bodyText;

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

    public String getTitleDisplayString(){
        return _authorDisplayName + " posted a new update:";
    }

    public String getBodyText(){
        return _bodyText;
    }

    public BitmapDrawable getBodyImage(){
        return _bodyImage;
    }

    @Override
    public Bundle convertToBundle() {
        Bundle bundle = super.convertToBundle();
        if(bundle == null){
            return null;
        }

        bundle.putString(bodyTextKey, _bodyText);
        bundle.putParcelable(bodyImageKey, _bodyImage.getBitmap());

        return bundle;
    }
}
