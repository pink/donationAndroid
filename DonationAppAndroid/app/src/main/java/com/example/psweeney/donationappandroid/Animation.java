package com.example.psweeney.donationappandroid;

import android.view.View;

/**
 * Created by psweeney on 4/22/16.
 *
 * There was going to be a little more to this class (I wanted it to have a set of static animation
 * methods that could be called with various View objects as a parameter and used across the app) but
 * I only really ended up needed the one defaultButtonAnimation() method
 *
 */
public class Animation {
    public static final float DEFAULT_BUTTON_ANIMATION_START_ALPHA = 0.5f;

    public static void defaultButtonAnimation(View v){
        if(v == null){
            return;
        }

        v.setAlpha(DEFAULT_BUTTON_ANIMATION_START_ALPHA);
        v.animate().alpha(1);
    }
}
