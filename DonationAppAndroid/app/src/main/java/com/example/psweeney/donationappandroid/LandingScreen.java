package com.example.psweeney.donationappandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by psweeney
 *
 * This activity simply holds the navigation buttons for each of the four different sections of
 * the app (not the same as the four primary tasks) as well as the auto donate on/off switch
 *
 */

public class LandingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        try{
            boolean autoDonateEnabled = getPreferences(MODE_PRIVATE).getBoolean(getString(R.string.user_auto_donate_key), false);
            Switch sw = (Switch) findViewById(R.id.switchAutoDonate);
            sw.setChecked(autoDonateEnabled);
            if(autoDonateEnabled){
                sw.setText(getResources().getString(R.string.auto_donate_label_on));
            } else {
                sw.setText(getResources().getString(R.string.auto_donate_label_off));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickAutoDonateSwitch(View v){
        Switch sw = (Switch) v;
        SharedPreferences.Editor prefsEditor = getPreferences(MODE_PRIVATE).edit();
        prefsEditor.putBoolean(getString(R.string.user_auto_donate_key), sw.isChecked());
        prefsEditor.apply();

        String msg = "Auto-donate has been set to ";

        if(sw.isChecked()){
            sw.setText(getResources().getString(R.string.auto_donate_label_on));
            sw.setBackgroundColor(0);
            msg += "ON";
        } else {
            sw.setText(getResources().getString(R.string.auto_donate_label_off));
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
