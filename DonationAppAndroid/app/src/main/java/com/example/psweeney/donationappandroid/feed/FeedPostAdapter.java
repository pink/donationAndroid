package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by psweeney on 4/18/16.
 */
public class FeedPostAdapter extends ArrayAdapter{
    private List<PostData> _postDataList;
    public FeedPostAdapter(Context context, int resource, List<PostData> postDataList) {
        super(context, resource, postDataList);
        _postDataList = postDataList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        PostData curr = _postDataList.get(position);

        View rowView = null;
        if(curr instanceof DonationPostData){
            rowView = inflater.inflate(R.layout.feed_post_donation, parent, false);

            ImageView imageViewAuthorIcon = (ImageView) rowView.findViewById(R.id.imageViewAuthorIcon);
            imageViewAuthorIcon.setImageDrawable(curr.getAuthorIcon());

            TextView textViewTopLine = (TextView) rowView.findViewById(R.id.textViewTitleLine);
            textViewTopLine.setText(curr.getTitleDisplayString());

            TextView textViewBottomLine = (TextView) rowView.findViewById(R.id.textViewDateLine);
            textViewBottomLine.setText(curr.getDateDisplayString());
        } else if(curr instanceof CharityPostData){
            rowView = inflater.inflate(R.layout.feed_post_charity, parent, false);

            ImageView imageViewAuthorIcon = (ImageView) rowView.findViewById(R.id.imageViewAuthorIcon);
            imageViewAuthorIcon.setImageDrawable(curr.getAuthorIcon());

            TextView textViewTopLine = (TextView) rowView.findViewById(R.id.textViewTitleLine);
            textViewTopLine.setText(curr.getTitleDisplayString());

            ImageView imageViewBody = (ImageView) rowView.findViewById(R.id.imageViewBody);

            if(((CharityPostData) curr).getBodyImage() == null){
                imageViewBody.setVisibility(View.GONE);
            } else {
                imageViewBody.setImageDrawable(((CharityPostData) curr).getBodyImage());
                imageViewBody.setVisibility(View.VISIBLE);
            }

            TextView textViewBody = (TextView) rowView.findViewById(R.id.textViewBody);
            textViewBody.setText(((CharityPostData) curr).getBodyText());

            TextView textViewBottomLine = (TextView) rowView.findViewById(R.id.textViewDateLine);
            textViewBottomLine.setText(curr.getDateDisplayString());
        }

        PostContainer parentContainer = (PostContainer) rowView.findViewById(R.id.parentContainer);
        parentContainer.setData(curr);

        TextView textViewNumLikes = (TextView) rowView.findViewById(R.id.textViewLikeNum);

        if(curr.getNumLikes() <= 0){
            textViewNumLikes.setVisibility(View.GONE);
        } else {
            textViewNumLikes.setText(Integer.toString(curr.getNumLikes()));
        }

        ImageView imageViewLikeIconDisabled = (ImageView) rowView.findViewById(R.id.imageViewLikeIconDisabled);
        ImageView imageViewLikeIconEnabled = (ImageView) rowView.findViewById(R.id.imageViewLikeIconEnabled);

        if(curr.likedByUser()){
            imageViewLikeIconDisabled.setVisibility(View.GONE);
            imageViewLikeIconEnabled.setVisibility(View.VISIBLE);
        } else {
            imageViewLikeIconDisabled.setVisibility(View.VISIBLE);
            imageViewLikeIconEnabled.setVisibility(View.GONE);
        }

        TextView textViewNumComments = (TextView) rowView.findViewById(R.id.textViewCommentNum);
        if(curr.getComments().size() <= 0){
            textViewNumComments.setVisibility(View.GONE);
        } else {
            textViewNumComments.setText(Integer.toString(curr.getComments().size()));
        }

        return rowView;
    }

    public static PostContainer getParentPostContainer(View v){
        if(v instanceof PostContainer){
            return (PostContainer) v;
        }

        if(v == null){
            return null;
        }

        return getParentPostContainer((View) v.getParent());
    }

    public static String buildPostTimeString(Calendar calendar){
        String dateString = "";
        switch (calendar.get(Calendar.MONTH)){
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

        dateString += " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR) + " at " +
                calendar.get(Calendar.HOUR) + ":";

        if(calendar.get(Calendar.MINUTE) < 10){
            dateString += "0";
        }
        dateString += calendar.get(Calendar.MINUTE);

        if(calendar.get(Calendar.AM_PM) == Calendar.AM){
            dateString += " AM";
        } else {
            dateString += " PM";
        }

        return dateString;
    }

    public static Bundle convertPostDataToBundle(PostData data){
        if(data.getPostType() == PostData.PostType.DONATION){
            return convertDonationPostDataToBundle((DonationPostData) data);
        }

        if(data.getPostType() == PostData.PostType.CHARITY){
            return convertCharityPostDataToBundle((CharityPostData) data);
        }

        return new Bundle();
    }

    public static PostData extractPostDataFromBundle(Bundle bundle){
        if(bundle == null){
            return null;
        }

        if(bundle.getString("postType").equals(PostData.PostType.DONATION.toString())){
            return extractDonationPostDataFromBundle(bundle);
        }

        if(bundle.getString("postType").equals(PostData.PostType.CHARITY.toString())){
            return extractCharityPostDataFromBundle(bundle);
        }

        return null;
    }

    private static Bundle convertDonationPostDataToBundle(DonationPostData data){
        Bundle bundle = new Bundle();
        if(bundle == null || data == null){
            return null;
        }

        bundle.putString("postType", PostData.PostType.DONATION.toString());

        bundle.putParcelable(PostData.authorIconKey, data.getAuthorIcon().getBitmap());
        bundle.putString(PostData.authorDisplayNameKey, data.getAuthorDisplayName());
        bundle.putString(DonationPostData.recipientDisplayNameKey, data.getRecipientDisplayName());
        bundle.putSerializable(PostData.postTimeKey, data.getPostTime());
        bundle.putInt(DonationPostData.donationAmountCentsKey, data.getDonationAmountCents());
        bundle.putInt(PostData.numLikesKey, data.getNumLikes());
        bundle.putBoolean(PostData.likedByUserKey, data.likedByUser());
        bundle.putInt(PostData.numCommentsKey, data.getComments().size());

        ArrayList<CommentData> comments = data.getComments();
        for(int i = 0; i < comments.size(); i++){
            CommentData curr = comments.get(i);
            bundle.putParcelable(CommentData.getAuthorIconBundleKey(i), curr.getAuthorIcon().getBitmap());
            bundle.putString(CommentData.getAuthorDisplayNameBundleKey(i), curr.getAuthorDisplayName());
            bundle.putString(CommentData.getCommentTextBundleKey(i), curr.getCommentText());
        }

        return bundle;
    }

    private static Bundle convertCharityPostDataToBundle(CharityPostData data){
        Bundle bundle = new Bundle();
        if(bundle == null || data == null){
            return null;
        }
        bundle.putString("postType", PostData.PostType.CHARITY.toString());

        bundle.putParcelable(PostData.authorIconKey, data.getAuthorIcon().getBitmap());
        bundle.putString(PostData.authorDisplayNameKey, data.getAuthorDisplayName());
        bundle.putString(CharityPostData.bodyTextKey, data.getBodyText());
        bundle.putParcelable(CharityPostData.bodyImageKey, data.getBodyImage().getBitmap());
        bundle.putSerializable(PostData.postTimeKey, data.getPostTime());
        bundle.putInt(PostData.numLikesKey, data.getNumLikes());
        bundle.putBoolean(PostData.likedByUserKey, data.likedByUser());
        bundle.putInt(PostData.numCommentsKey, data.getComments().size());

        ArrayList<CommentData> comments = data.getComments();
        for(int i = 0; i < comments.size(); i++){
            CommentData curr = comments.get(i);
            bundle.putParcelable(CommentData.getAuthorIconBundleKey(i), curr.getAuthorIcon().getBitmap());
            bundle.putString(CommentData.getAuthorDisplayNameBundleKey(i), curr.getAuthorDisplayName());
            bundle.putString(CommentData.getCommentTextBundleKey(i), curr.getCommentText());
        }

        return bundle;
    }

    private static DonationPostData extractDonationPostDataFromBundle(Bundle bundle){
        if(bundle == null || !bundle.containsKey("postType") ||
                !bundle.getString("postType").equals(PostData.PostType.DONATION.toString())){
            return null;
        }

        BitmapDrawable authorIcon = new BitmapDrawable((Bitmap) bundle.getParcelable(PostData.authorIconKey));
        String authorDisplayName = bundle.getString(PostData.authorDisplayNameKey);
        String recipientDisplayName = bundle.getString(DonationPostData.recipientDisplayNameKey);
        Calendar postTime = (Calendar) bundle.getSerializable(PostData.postTimeKey);
        int donationAmountCents = bundle.getInt(DonationPostData.donationAmountCentsKey);
        int numLikes = bundle.getInt(PostData.numLikesKey);
        boolean likedByUser = bundle.getBoolean(PostData.likedByUserKey);

        ArrayList<CommentData> comments = CommentData.extractCommentsFromBundle(bundle);

        return new DonationPostData(authorIcon, authorDisplayName, recipientDisplayName, postTime,
                donationAmountCents, numLikes, likedByUser, comments);
    }

    private static CharityPostData extractCharityPostDataFromBundle(Bundle bundle){
        if(bundle == null || !bundle.containsKey("postType") ||
                !bundle.getString("postType").equals(PostData.PostType.CHARITY.toString())){
            return null;
        }

        BitmapDrawable authorIcon = new BitmapDrawable((Bitmap) bundle.getParcelable(PostData.authorIconKey));
        String authorDisplayName = bundle.getString(PostData.authorDisplayNameKey);
        String bodyText = bundle.getString(CharityPostData.bodyTextKey);
        BitmapDrawable bodyImage = new BitmapDrawable((Bitmap) bundle.getParcelable(CharityPostData.bodyImageKey));
        Calendar postTime = (Calendar) bundle.getSerializable(PostData.postTimeKey);
        int numLikes = bundle.getInt(PostData.numLikesKey);
        boolean likedByUser = bundle.getBoolean(PostData.likedByUserKey);

        ArrayList<CommentData> comments = CommentData.extractCommentsFromBundle(bundle);

        return new CharityPostData(authorIcon, authorDisplayName, bodyImage,bodyText, postTime, numLikes,
                likedByUser, comments);
    }
}
