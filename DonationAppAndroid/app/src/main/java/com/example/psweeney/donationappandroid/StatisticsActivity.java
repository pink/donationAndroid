package com.example.psweeney.donationappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void onClickCategoryGoals(View v){
        startActivity(new Intent(getApplicationContext(), StatisticsActivity.class));
    }

    public void onClickCategoryHistory(View v){
        startActivity(new Intent(getApplicationContext(), StatisticsActivity.class));
    }

    public void onClickCategoryBreakdown(View v){
        startActivity(new Intent(getApplicationContext(), StatisticsActivity.class));
    }
}
