package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by psweeney on 4/18/16.
 */
public class FeedPostAdapter extends ArrayAdapter{
    List<PostData> _postDataList;
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

            /*
            TextView textViewNumLikes = (TextView) rowView.findViewById(R.id.textViewLikeNum);

            if(curr.getNumLikes() <= 0){
                textViewNumLikes.setVisibility(View.GONE);
            } else {
                textViewNumLikes.setText(curr.getNumLikes());
            }

            ImageView imageViewLikeIcon = (ImageView) rowView.findViewById(R.id.imageViewLikeIcon);
            if(curr.likedByUser()){
                imageViewLikeIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_white_48dp));
            } else {
                imageViewLikeIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_border_white_48dp));
            }

            final PostData currDataFinal = curr;
            final ImageView likeIconFinal = imageViewLikeIcon;
            final TextView likeTextFinal = textViewNumLikes;

            View likeFrame = rowView.findViewById(R.id.frameLayoutLikeContainer);
            likeFrame.setOnClickListener(new View.OnClickListener() {
                PostData data = currDataFinal;
                ImageView likeIcon = likeIconFinal;
                TextView likeText = likeTextFinal;

                @Override
                public void onClick(View v) {
                    data.setLikedByUser(!data.likedByUser());
                    if(data.likedByUser()){
                        likeIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_white_48dp));
                        data.setNumLikes(data.getNumLikes() + 1);
                    } else {
                        likeIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_border_white_48dp));
                        data.setNumLikes(data.getNumLikes() - 1);
                    }

                    if(data.getNumLikes() > 0){
                        likeText.setText(data.getNumLikes());
                        likeText.setVisibility(View.VISIBLE);
                    } else {
                        likeText.setVisibility(View.GONE);
                    }
                }
            });

            TextView textViewNumComments = (TextView) rowView.findViewById(R.id.textViewCommentNum);
            if(curr.getComments().size() <= 0){
                textViewNumComments.setVisibility(View.GONE);
            } else {
                textViewNumComments.setText(curr.getComments().size());
            } */
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

            /*
            TextView textViewNumLikes = (TextView) rowView.findViewById(R.id.textViewLikeNum);

            if(curr.getNumLikes() <= 0){
                textViewNumLikes.setVisibility(View.GONE);
            } else {
                textViewNumLikes.setText(curr.getNumLikes());
            }

            final PostData currFinal = curr;

            View likeFrame = rowView.findViewById(R.id.frameLayoutLikeContainer);
            likeFrame.setOnClickListener(new View.OnClickListener() {
                PostData data = currFinal;
                @Override
                public void onClick(View v) {
                    ImageView imv = (ImageView) v.findViewById(R.id.imageViewLikeIcon);
                    data.setLikedByUser(!data.likedByUser());
                    if(data.likedByUser()){
                        imv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_white_48dp));
                        data.setNumLikes(data.getNumLikes() + 1);
                    } else {
                        imv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_favorite_border_white_48dp));
                        data.setNumLikes(data.getNumLikes() - 1);
                    }

                    TextView textViewNumLikesData = (TextView) v.findViewById(R.id.textViewLikeNum);
                    if(data.getNumLikes() > 0){
                        textViewNumLikesData.setText(data.getNumLikes());
                        textViewNumLikesData.setVisibility(View.VISIBLE);
                    } else {
                        textViewNumLikesData.setVisibility(View.GONE);
                    }
                }
            });

            TextView textViewNumComments = (TextView) rowView.findViewById(R.id.textViewCommentNum);
            if(curr.getComments().size() <= 0){
                textViewNumComments.setVisibility(View.GONE);
            } else {
                textViewNumComments.setText(curr.getComments().size());
            } */
        }

        TextView textViewNumLikes = (TextView) rowView.findViewById(R.id.textViewLikeNum);

        if(curr.getNumLikes() <= 0){
            textViewNumLikes.setVisibility(View.GONE);
        } else {
            textViewNumLikes.setText(curr.getNumLikes());
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
            textViewNumComments.setText(curr.getComments().size());
        }

        return rowView;
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
}
