package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

import java.util.ArrayList;
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
            imageViewAuthorIcon.setImageDrawable(getContext().getResources().getDrawable(((DonationPostData) curr).getAuthorIconId()));

            TextView textViewTopLine = (TextView) rowView.findViewById(R.id.firstLine);
            textViewTopLine.setText(((DonationPostData) curr).buildTopString());

            TextView textViewBottomLine = (TextView) rowView.findViewById(R.id.secondLine);
            textViewBottomLine.setText(((DonationPostData) curr).buildBottomString());
        }

        return rowView;
    }
}
