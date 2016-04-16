package com.example.psweeney.donationappandroid;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.EventListener;

public class SplashScreen extends AppCompatActivity {

    private Bitmap _logoBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void onButtonClickLoginRegister(View v) {
        if(v == null || v.getId() != R.id.buttonLoginRegister){
            return;
        }
        startActivity(new Intent(getApplicationContext(), LandingScreen.class));
        finish();
    }
}
