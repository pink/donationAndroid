package com.example.psweeney.donationappandroid;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
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
                View loginControls = getActivity().findViewById(R.id.loginControlsContainer);

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


