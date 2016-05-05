package com.example.psweeney.donationappandroid.feed;

import com.example.psweeney.donationappandroid.charity.CharityDetailData;
import com.example.psweeney.donationappandroid.charity.CharityDetailFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by psweeney on 4/18/16.
 */
public class DonationPostData extends PostData{

    private Integer _recipientIdentifier;
    private int _donationAmountCents;

    public static final String USER_POST_NAME = "You";

    public DonationPostData(){
        _recipientIdentifier = 0;
        _donationAmountCents = 0;
    }

    public DonationPostData(int authorIconId, String authorDisplayName, Integer recipientIdentifier, Calendar postTime, int donationAmountCents,
                            int numLikes, boolean likedByUser, List<CommentData> comments){
        this();
        _authorIconId = authorIconId;
        if(authorDisplayName == null){
            _authorDisplayName = new String(USER_POST_NAME);
        } else {
            _authorDisplayName = new String(authorDisplayName);
        }

        _recipientIdentifier = recipientIdentifier;
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

    public DonationPostData(int authorIconId, String authorDisplayName, Integer recipientIdentifier, Calendar postTime, int donationAmountCents){
        this(authorIconId, authorDisplayName, recipientIdentifier, postTime, donationAmountCents, 0, false, null);
    }

    public DonationPostData(int authorIconId, String authorDisplayName, Integer recipientIdentifier, int donationAmountCents){
        this(authorIconId, authorDisplayName, recipientIdentifier, Calendar.getInstance(), donationAmountCents);
    }

    public DonationPostData(DonationPostData other){
        this(other._authorIconId, other._authorDisplayName, other._recipientIdentifier, other._postTime, other._donationAmountCents,
                other._numLikes, other._likedByUser, other._comments);
    }

    /*
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
        _recipientIdentifier = recipientDisplayName;
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
    */

    public String getRecipientDisplayName(){
        CharityDetailData charity = CharityDetailFactory.getCharityById(_recipientIdentifier);
        if(charity == null){
            return "NAME_ERROR";
        }
        return charity.getDisplayName();
    }

    public int getDonationAmountCents(){
        return _donationAmountCents;
    }

    public static String getDonationAmountDisplayString(int donationAmountCents){
        int dollarNum = donationAmountCents / 100, centsNum = donationAmountCents % 100;
        String ret = "$" + dollarNum + ".";

        if(centsNum < 10){
            ret += "0";
        }
        ret += centsNum;
        return ret;
    }

    public String getTitleDisplayString(){
        return _authorDisplayName + " donated " + getDonationAmountDisplayString(_donationAmountCents) + " to " + getRecipientDisplayName() + ".";
    }

    @Override
    public Integer getPostIdentifier() {
        Integer ret = super.getPostIdentifier();
        ret += ((Integer) getDonationAmountCents()).hashCode();
        ret += getRecipientDisplayName().hashCode();

        return ret;
    }
}
