package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

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

    public void setData(PostData data){
        _data = data;
    }

    public PostData getData(){
        return _data;
    }
}
