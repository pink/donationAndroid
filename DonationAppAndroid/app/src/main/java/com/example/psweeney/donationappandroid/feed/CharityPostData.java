package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.example.psweeney.donationappandroid.R;
import com.example.psweeney.donationappandroid.charity.CharityDetailData;
import com.example.psweeney.donationappandroid.charity.CharityDetailFactory;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by psweeney on 4/18/16.
 */
public class CharityPostData extends PostData {
    public static int charityAuthorIdDefault = R.drawable.ic_local_florist_black_48dp;
    public static int bodyImageIdDefault = R.drawable.ic_photo_library_black_48dp;

    private CharityDetailData _author;
    private String _bodyText;
    private boolean _useBodyText = false;
    private int _bodyImageId;
    private boolean _useBodyImage = false;

    public CharityPostData(){
        _bodyImageId = charityAuthorIdDefault;
        _bodyText = "";
        _postTime = Calendar.getInstance();
        _numLikes = 0;
        _likedByUser = false;
        _comments = new ArrayList<>();
    }

    public CharityPostData(Integer authorId, int bodyImageId, boolean useBodyImage, String bodyText,
                           boolean useBodyText, Calendar postTime, int numLikes, boolean likedByUser,
                           ArrayList<CommentData> comments){
        this();
        _author = CharityDetailFactory.getCharityById(authorId);
        _bodyText = bodyText;
        _useBodyText = useBodyText;
        _bodyImageId = bodyImageId;
        _useBodyImage = useBodyImage;
        _postTime = postTime;
        _numLikes = numLikes;
        _likedByUser = likedByUser;
        if(comments == null){
            _comments = new ArrayList<>();
        } else {
            _comments = comments;
        }
    }

    public CharityPostData(Integer authorId, int bodyImageId, String bodyText, Calendar postTime){
        this(authorId, bodyImageId, true, bodyText, true, postTime, 0, false, null);
    }

    public CharityPostData(Integer authorId, String bodyText, Calendar postTime){
        this(authorId, bodyImageIdDefault, false, bodyText, true, postTime, 0, false, null);
    }

    public CharityPostData(Integer authorId, int bodyImageId, Calendar postTime){
        this(authorId, bodyImageId, true, null, false, postTime, 0, false, null);
    }

    public CharityPostData(Integer authorId, int bodyImageId, String bodyText){
        this(authorId, bodyImageId, bodyText, Calendar.getInstance());
    }

    public CharityPostData(Integer authorId, String bodyText){
        this(authorId, bodyText, Calendar.getInstance());
    }

    public CharityPostData(Integer authorId, int bodyImageId){
        this(authorId, bodyImageId, Calendar.getInstance());
    }

    @Override
    public String getAuthorDisplayName() {
        return _author.getDisplayName();
    }

    @Override
    public int getAuthorIconId() {
        return _author.getIconId();
    }

    @Override
    public PostType getPostType() {
        return PostType.CHARITY;
    }

    public String getTitleDisplayString(){
        return getAuthorDisplayName() + " posted a new update:";
    }

    public String getBodyText(){
        return _bodyText;
    }

    public boolean useBodyText(){
        return _useBodyText;
    }

    public int getBodyImageId(){
        return _bodyImageId;
    }

    public boolean useBodyImage(){
        return _useBodyImage;
    }

    @Override
    public Integer getPostIdentifier() {
        Integer ret = super.getPostIdentifier();
        ret += _bodyText.hashCode();
        ret += ((Integer) _bodyImageId).hashCode();
        return ret;
    }
}
