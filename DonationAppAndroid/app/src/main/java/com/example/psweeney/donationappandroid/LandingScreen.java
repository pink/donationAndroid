package com.example.psweeney.donationappandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class LandingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void onClickAutoDonateSwitch(View v){
        Switch sw = (Switch) v;
        SharedPreferences.Editor prefsEditor = getPreferences(MODE_PRIVATE).edit();
        prefsEditor.putBoolean(getString(R.string.user_auto_donate_key), sw.isChecked());
        prefsEditor.apply();

        String msg = "Auto-donate has been set to ";
        if(sw.isChecked()){
            msg += "ON";
        } else {
            msg += "OFF";
        }

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void onClickCategoryStatistics(View v){
        Animation.defaultButtonAnimation(v);
        startActivity(new Intent(getApplicationContext(), StatisticsActivity.class));
    }

    public void onClickCategorySearch(View v){
        Animation.defaultButtonAnimation(v);
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }

    public void onClickCategoryFeed(View v){
        Animation.defaultButtonAnimation(v);
        startActivity(new Intent(getApplicationContext(), FeedActivity.class));
    }

    public void onClickCategoryAccountSettings(View v){
        Animation.defaultButtonAnimation(v);
        Toast.makeText(getApplicationContext(), "This category has not yet been implemented", Toast.LENGTH_SHORT).show();
    }
}
