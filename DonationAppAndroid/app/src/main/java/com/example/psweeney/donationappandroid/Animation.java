package com.example.psweeney.donationappandroid;

import android.view.View;

/**
 * Created by psweeney on 4/22/16.
 */
public class Animation {
    public static float defaultButtonAnimationStartAlpha = 0.5f;

    public static void defaultButtonAnimation(View v){
        if(v == null){
            return;
        }

        v.setAlpha(defaultButtonAnimationStartAlpha);
        v.animate().alpha(1);
    }
}
