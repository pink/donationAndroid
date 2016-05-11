package com.example.psweeney.donationappandroid.charity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by psweeney on 4/22/16.
 *
 * Simple LinearLayout extension that contains a CharityDetailContainer reference which can be retrieved
 * programmatically
 *
 */


public class CharityDetailContainer extends LinearLayout {
    private CharityDetailData _data;

    public CharityDetailContainer(Context context) {
        super(context);
    }

    public CharityDetailContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharityDetailContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CharityDetailData getData() {
        return _data;
    }

    public void setData(CharityDetailData data) {
        _data = data;
    }
}
