package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

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

        PostContainer parentContainer = null;
        if(curr.getPostType() == PostData.PostType.DONATION){
            parentContainer = (PostContainer) inflater.inflate(R.layout.feed_post_donation, parent, false);
        } else if(curr.getPostType() == PostData.PostType.CHARITY){
            parentContainer = (PostContainer) inflater.inflate(R.layout.feed_post_charity, parent, false);
        }

        if(parentContainer == null){
            return null;
        }
        parentContainer.setData(curr);
        parentContainer.updateViews();

        return parentContainer;
        /*
        if(curr instanceof DonationPostData){
            rowView = inflater.inflate(R.layout.feed_post_donation, parent, false);

            ImageView imageViewAuthorIcon = (ImageView) rowView.findViewById(R.id.imageViewAuthorIcon);
            imageViewAuthorIcon.setImageDrawable(getContext().getResources().getDrawable(curr.getAuthorIconId()));

            TextView textViewTopLine = (TextView) rowView.findViewById(R.id.textViewTitleLine);
            textViewTopLine.setText(curr.getTitleDisplayString());

            TextView textViewBottomLine = (TextView) rowView.findViewById(R.id.textViewDateLine);
            textViewBottomLine.setText(curr.getDateDisplayString());
        } else if(curr instanceof CharityPostData){
            rowView = inflater.inflate(R.layout.feed_post_charity, parent, false);

            ImageView imageViewAuthorIcon = (ImageView) rowView.findViewById(R.id.imageViewAuthorIcon);
            imageViewAuthorIcon.setImageDrawable(getContext().getResources().getDrawable(curr.getAuthorIconId()));

            TextView textViewTopLine = (TextView) rowView.findViewById(R.id.textViewTitleLine);
            textViewTopLine.setText(curr.getTitleDisplayString());

            TextView textViewBody = (TextView) rowView.findViewById(R.id.textViewBody);

            if(((CharityPostData) curr).useBodyText()){
                textViewBody.setText(((CharityPostData) curr).getBodyText());
                textViewBody.setVisibility(View.VISIBLE);
            } else {
                textViewBody.setVisibility(View.GONE);
            }

            ImageView imageViewBody = (ImageView) rowView.findViewById(R.id.imageViewBody);

            if(((CharityPostData) curr).useBodyImage()){
                imageViewBody.setImageDrawable(getContext().getResources().getDrawable(((CharityPostData) curr).getBodyImageId()));
                imageViewBody.setVisibility(View.VISIBLE);
            } else {
                imageViewBody.setVisibility(View.GONE);
            }

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
        */
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
}
