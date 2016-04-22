package com.example.psweeney.donationappandroid.feed;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by psweeney on 4/18/16.
 */
public class DonationPostData extends PostData{
    public static String recipientDisplayNameKey = "recipientDisplayName";
    public static String donationAmountCentsKey = "donationAmountCents";

    private String _recipientDisplayName;
    private int _donationAmountCents;

    private static final String USER_POST_NAME = "You";

    public DonationPostData(){
        _recipientDisplayName = "";
        _donationAmountCents = 0;
    }

    public DonationPostData(int authorIconId, String authorDisplayName, String recipientDisplayName, Calendar postTime, int donationAmountCents,
                            int numLikes, boolean likedByUser, ArrayList<CommentData> comments){
        this();
        _authorIconId = authorIconId;
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

    public DonationPostData(int authorIconId, String authorDisplayName, String recipientDisplayName, Calendar postTime, int donationAmountCents){
        this(authorIconId, authorDisplayName, recipientDisplayName, postTime, donationAmountCents, 0, false, null);
    }

    public DonationPostData(int authorIconId, String authorDisplayName, String recipientDisplayName, int donationAmountCents){
        this(authorIconId, authorDisplayName, recipientDisplayName, Calendar.getInstance(), donationAmountCents);
    }

    public DonationPostData(DonationPostData other){
        this(other._authorIconId, other._authorDisplayName, other._recipientDisplayName, other._postTime, other._donationAmountCents);
    }

    public DonationPostData(Bundle bundle){
        this();
        if(bundle == null || !(bundle.containsKey("postType") && bundle.getString("postType").equals(PostType.DONATION.toString()))){
            return;
        }

        int authorIconId;
        String authorDisplayName;
        String recipientDisplayName;
        int donationAmountCents;
        Calendar postTime;
        int numLikes;
        boolean likedByUser;
        int numComments;

        try{
            authorIconId = bundle.getInt(PostData.authorIconIdKey);
            authorDisplayName = bundle.getString(PostData.authorDisplayNameKey);
            recipientDisplayName = bundle.getString(recipientDisplayNameKey);
            donationAmountCents = bundle.getInt(donationAmountCentsKey);
            postTime = (Calendar) bundle.getSerializable(PostData.postTimeKey);
            numLikes = bundle.getInt(PostData.numLikesKey);
            likedByUser = bundle.getBoolean(PostData.likedByUserKey);
            numComments = bundle.getInt(PostData.numCommentsKey);
        } catch (Exception e){
            return;
        }

        _authorIconId = authorIconId;
        _authorDisplayName = authorDisplayName;
        _recipientDisplayName = recipientDisplayName;
        _donationAmountCents = donationAmountCents;
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
    }

    public String getRecipientDisplayName(){
        return _recipientDisplayName;
    }

    public int getDonationAmountCents(){
        return _donationAmountCents;
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

    @Override
    public Bundle convertToBundle() {
        Bundle bundle = super.convertToBundle();
        if(bundle == null){
            return null;
        }
        bundle.putString(PostData.postTypeKey, PostType.DONATION.toString());
        bundle.putString(recipientDisplayNameKey, _recipientDisplayName);
        bundle.putInt(donationAmountCentsKey, _donationAmountCents);

        return bundle;
    }
}
