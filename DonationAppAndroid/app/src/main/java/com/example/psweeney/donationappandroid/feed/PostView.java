package com.example.psweeney.donationappandroid.feed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by psweeney on 4/19/16.
 */
public class PostView extends View {
    private PostData _data;

    public PostView(Context context, PostData data) {
        super(context);
        init(null, 0, data);
    }

    public PostView(Context context, AttributeSet attrs, PostData data) {
        super(context, attrs);
        init(attrs, 0, data);
    }

    public PostView(Context context, AttributeSet attrs, int defStyleAttr, PostData data) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, data);
    }

    private void init(AttributeSet attrs, int defStyle, PostData data){
        _data = data;
    }
}
