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
import java.util.Currency;
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
        Context context = getContext();
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

        dateString += " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR) + " at ";
        if(calendar.get(Calendar.MINUTE) < 10){
            dateString += "0";
        }
        dateString += calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
        if(calendar.get(Calendar.AM_PM) == Calendar.AM){
            dateString += " AM";
        } else {
            dateString += " PM";
        }

        return dateString;
    }
}
