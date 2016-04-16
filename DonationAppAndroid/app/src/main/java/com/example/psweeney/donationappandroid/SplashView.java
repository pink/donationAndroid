package com.example.psweeney.donationappandroid;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by psweeney on 4/15/16.
 */
public class SplashView extends ImageView {
    private enum SplashState{
        SPLASH_NOT_DRAWN, SPLASH_DRAWN, SPLASH_FINISHED
    }

    private SplashState _currentState = SplashState.SPLASH_NOT_DRAWN;
    private long _startTimeMillis;

    private static final long MIN_SLEEP_MILLIS = 3000;

    public SplashView(Context context) {
        super(context);
        init(null, 0);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        _startTimeMillis = (long) (((double) System.nanoTime()) / ((double) 1000000));
    }

    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //TODO: pretend to check login info or whatever

        switch (_currentState){
            case SPLASH_NOT_DRAWN:
                _currentState = SplashState.SPLASH_DRAWN;

                invalidate();
                return;
            case SPLASH_DRAWN:
                long currentMillis = (long) (((double) System.nanoTime()) / ((double) 1000000));
                if((currentMillis - _startTimeMillis) >= MIN_SLEEP_MILLIS){
                    _currentState = SplashState.SPLASH_FINISHED;
                }

                invalidate();
                return;
            case SPLASH_FINISHED:
                Activity activity;
                try{
                    activity = getActivity();
                } catch (Exception e){
                    e.printStackTrace();
                    return;
                }

                SharedPreferences prefs = activity.getPreferences(Context.MODE_PRIVATE);
                String storedEmail = prefs.getString(activity.getString(R.string.user_email_key), null);

                if(storedEmail != null){
                    activity.startActivity(new Intent(activity.getApplicationContext(), LandingScreen.class));
                    activity.finish();
                    return;
                }

                View loginControls = activity.findViewById(R.id.loginControlsContainer);

                try{
                    if(loginControls.getVisibility() != VISIBLE) {
                        loginControls.setVisibility(VISIBLE);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

        }
    }
}


