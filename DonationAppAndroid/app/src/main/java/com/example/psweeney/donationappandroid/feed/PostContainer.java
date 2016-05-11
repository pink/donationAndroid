package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.psweeney.donationappandroid.R;

/**
 * Created by psweeney on 4/19/16.
 *
 * LinearLayout extension that stores a PostData object
 *
 */
public class PostContainer extends LinearLayout {
    private PostData _data;

    public PostContainer(Context context) {
        super(context);
        init(null, 0);
    }

    public PostContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PostContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle){

    }

    public void updateViews(){
        ImageView authorIcon = (ImageView) findViewById(R.id.imageViewAuthorIcon);

        TextView titleText = (TextView) findViewById(R.id.textViewTitleLine);
        TextView dateText = (TextView) findViewById(R.id.textViewDateLine);

        ImageView likeIconDisabled = (ImageView) findViewById(R.id.imageViewLikeIconDisabled);
        ImageView likeIconEnabled = (ImageView) findViewById(R.id.imageViewLikeIconEnabled);
        TextView likeNumText = (TextView) findViewById(R.id.textViewLikeNum);
        TextView commentNumText = (TextView) findViewById(R.id.textViewCommentNum);

        if(_data == null || authorIcon == null || titleText == null || dateText == null || likeIconDisabled == null
                || likeIconEnabled == null || likeNumText == null || commentNumText == null){
            return;
        }

        authorIcon.setImageDrawable(getResources().getDrawable(_data.getAuthorIconId()));

        titleText.setText(_data.getTitleDisplayString());
        dateText.setText(_data.getDateDisplayString());

        if(_data instanceof CharityPostData){
            TextView bodyText = (TextView) findViewById(R.id.textViewBody);
            ImageView bodyImage = (ImageView) findViewById(R.id.imageViewBody);

            if(bodyText != null ){
                if(((CharityPostData) _data).useBodyText() && ((CharityPostData) _data).getBodyText().length() > 0){
                    bodyText.setText(((CharityPostData) _data).getBodyText());
                    bodyText.setVisibility(VISIBLE);
                } else {
                    bodyText.setVisibility(GONE);
                }
            }

            if(bodyImage != null){
                if(((CharityPostData) _data).useBodyImage()){
                    bodyImage.setImageDrawable(getResources().getDrawable(((CharityPostData) _data).getBodyImageId()));
                    bodyImage.setVisibility(VISIBLE);
                } else {
                    bodyImage.setVisibility(GONE);
                }
            }
        }

        if(_data.likedByUser()){
            likeIconEnabled.setVisibility(View.VISIBLE);
            likeIconDisabled.setVisibility(View.GONE);
        } else {
            likeIconEnabled.setVisibility(View.GONE);
            likeIconDisabled.setVisibility(View.VISIBLE);
        }

        likeNumText.setText(Integer.toString(_data.getNumLikes()));
        if(_data.getNumLikes() > 0){
            likeNumText.setVisibility(View.VISIBLE);
        } else {
            likeNumText.setVisibility(View.GONE);
        }

        commentNumText.setText(Integer.toString(_data.getComments().size()));
        if(_data.getComments().size() > 0){
            commentNumText.setVisibility(View.VISIBLE);
        } else {
            commentNumText.setVisibility(View.GONE);
        }

        invalidate();
    }

    public void setData(PostData data){
        _data = data;
    }

    public PostData getData(){
        return _data;
    }
}
