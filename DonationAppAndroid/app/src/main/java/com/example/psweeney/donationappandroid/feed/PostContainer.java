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
        ImageView likeIconDisabled = (ImageView) findViewById(R.id.imageViewLikeIconDisabled);
        ImageView likeIconEnabled = (ImageView) findViewById(R.id.imageViewLikeIconEnabled);
        TextView likeNumText = (TextView) findViewById(R.id.textViewLikeNum);
        TextView commentNumText = (TextView) findViewById(R.id.textViewCommentNum);

        if(likeIconDisabled == null || likeIconEnabled == null || likeNumText == null || commentNumText == null){
            return;
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
