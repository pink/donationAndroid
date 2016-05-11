package com.example.psweeney.donationappandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by psweeney on 5/3/16.
 *
 * Just an ImageView extension where the background drawable is set according to the value of _progressRatio,
 * where 0 <= _progressRatio <= 1
 */
public class PercentageView extends ImageView {
    private float _progressRatio = 1;

    private int _backgroundColor = getContext().getResources().getColor(R.color.colorAccent);
    private int _progressColor = getContext().getResources().getColor(R.color.charityDetailPersonalDonationPercentageBar);
    private int _emptyColor = getContext().getResources().getColor(R.color.charityDetailPersonalDonationPercentageBar);

    private float _emptyAlpha = 0.25f;

    public PercentageView(Context context) {
        super(context);
    }

    public PercentageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float getProgressRatio(){
        return _progressRatio;
    }

    public void setProgressRatio(float progressRatio) {
        _progressRatio = Math.min(1, Math.max(0, progressRatio));
        invalidate();
    }

    public int getBackgroundColor(){
        return _backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor){
        _backgroundColor = backgroundColor;
        invalidate();
    }

    public int getProgressColor() {
        return _progressColor;
    }

    public void setProgressColor(int progressColor) {
        _progressColor = progressColor;
        invalidate();
    }

    public int getEmptyColor() {
        return _emptyColor;
    }

    public void setEmptyColor(int emptyColor) {
        _emptyColor = emptyColor;
        invalidate();
    }

    public float getEmptyAlpha(){
        return _emptyAlpha;
    }

    public void setEmptyAlpha(float backgroundAlpha){
        _emptyAlpha = backgroundAlpha;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(canvas == null){
            return;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(_backgroundColor);

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        paint.setColor(_emptyColor);
        paint.setAlpha((int) (_emptyAlpha * 255));

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        paint.setColor(_progressColor);
        paint.setAlpha(255);

        canvas.drawRect(0, 0, ((float) canvas.getWidth()) * _progressRatio, canvas.getHeight(), paint);
    }

    public static String getPercentageStringForRatio(float ratio){
        int displayInts = Math.round(ratio * 100);
        int displayHundredths = Math.round(ratio * 10000) % 100;

        String ret = Integer.toString(displayInts) + ".";
        if(displayHundredths < 10){
            ret += '0';
        }
        ret += Integer.toString(displayHundredths) + '%';

        return ret;
    }
}
