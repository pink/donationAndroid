package com.example.psweeney.donationappandroid.feed;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.example.psweeney.donationappandroid.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by psweeney on 4/18/16.
 */
public class CharityPostData extends PostData {
    public static String bodyTextKey = "bodyText";
    public static String useBodyTextKey = "useBodyText";
    public static String bodyImageIdKey = "bodyImage";
    public static String useBodyImageKey = "useBodyImage";

    public static int charityAuthorIdDefault = R.drawable.ic_local_florist_black_48dp;
    public static int bodyImageIdDefault = R.drawable.ic_photo_library_black_48dp;

    private String _bodyText;
    private boolean _useBodyText = false;
    private int _bodyImageId;
    private boolean _useBodyImage = false;

    public CharityPostData(){
        _authorDisplayName = "";
        _bodyImageId = charityAuthorIdDefault;
        _bodyText = "";
        _postTime = Calendar.getInstance();
        _numLikes = 0;
        _likedByUser = false;
        _comments = new ArrayList<>();
    }

    public CharityPostData(int authorIconId, String authorDisplayName, int bodyImageId, boolean useBodyImage, String bodyText,
                           boolean useBodyText, Calendar postTime, int numLikes, boolean likedByUser,
                           ArrayList<CommentData> comments){
        this();
        _authorIconId = authorIconId;
        _authorDisplayName = authorDisplayName;
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

    public CharityPostData(int authorIconId, String authorDisplayName, int bodyImageId, String bodyText, Calendar postTime){
        this(authorIconId, authorDisplayName, bodyImageId, true, bodyText, true, postTime, 0, false, null);
    }

    public CharityPostData(int authorIconId, String authorDisplayName, String bodyText, Calendar postTime){
        this(authorIconId, authorDisplayName, bodyImageIdDefault, false, bodyText, true, postTime, 0, false, null);
    }

    public CharityPostData(int authorIconId, String authorDisplayName, int bodyImageId, Calendar postTime){
        this(authorIconId, authorDisplayName, bodyImageId, true, null, false, postTime, 0, false, null);
    }

    public CharityPostData(int authorIconId, String authorDisplayName, int bodyImageId, String bodyText){
        this(authorIconId, authorDisplayName, bodyImageId, bodyText, Calendar.getInstance());
    }

    public CharityPostData(int authorIconId, String authorDisplayName, String bodyText){
        this(authorIconId, authorDisplayName, bodyText, Calendar.getInstance());
    }

    public CharityPostData(int authorIconId, String authorDisplayName, int bodyImageId){
        this(authorIconId, authorDisplayName, bodyImageId, Calendar.getInstance());
    }

    /*
    public CharityPostData(Bundle bundle){
        this();

        if(bundle == null || !(bundle.containsKey("postType") && bundle.getString("postType").equals(PostType.CHARITY.toString()))){
            return;
        }

        int authorIconId;
        String authorDisplayName;
        String bodyText;
        boolean useBodyText;
        int bodyImageId;
        boolean useBodyImage;
        Calendar postTime;
        int numLikes;
        boolean likedByUser;
        int numComments;

        try{
            authorIconId = bundle.getInt(PostData.authorIconIdKey);
            authorDisplayName = bundle.getString(PostData.authorDisplayNameKey);
            bodyText = bundle.getString(CharityPostData.bodyTextKey);
            useBodyText = bundle.getBoolean(useBodyTextKey);
            bodyImageId = bundle.getInt(bodyImageIdKey);
            useBodyImage = bundle.getBoolean(useBodyImageKey);
            postTime = (Calendar) bundle.getSerializable(PostData.postTimeKey);
            numLikes = bundle.getInt(PostData.numLikesKey);
            likedByUser = bundle.getBoolean(PostData.likedByUserKey);
            numComments = bundle.getInt(PostData.numCommentsKey);
        } catch (Exception e){
            return;
        }

        _authorIconId = authorIconId;
        _authorDisplayName = authorDisplayName;
        _bodyText = bodyText;
        _useBodyText = useBodyText;
        _bodyImageId = bodyImageId;
        _useBodyImage = useBodyImage;
        _postTime = postTime;
        _numLikes = numLikes;
        _likedByUser = likedByUser;

        for(int i = 0; i < numComments; i++){
            CommentData cd = null;
            try {
                cd = CommentData.extractCommentDataFromBundle(bundle, i);
            } catch (Exception e){
                continue;
            }
            _comments.add(cd);
        }
    } */

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
