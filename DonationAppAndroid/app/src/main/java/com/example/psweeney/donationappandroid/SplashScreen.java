package com.example.psweeney.donationappandroid;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EventListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Email validator copied from http://stackoverflow.com/questions/6119722

public class SplashScreen extends AppCompatActivity {

    private Bitmap _logoBitmap;

    private static final int MIN_PASSWORD_LENGTH = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public void onButtonClickLoginRegister(View v) {
        if(v == null || v.getId() != R.id.buttonLoginRegister){
            return;
        }

        if(!isEmailValid(((EditText) findViewById(R.id.editTextEmailField)).getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.invalid_email_message, Toast.LENGTH_SHORT).show();
        } else if(((EditText) findViewById(R.id.editTextPasswordField)).getText().toString().length() < MIN_PASSWORD_LENGTH){
            String invalidPasswordMessage = getString(R.string.invalid_password_message_1) + MIN_PASSWORD_LENGTH +
                    getString(R.string.invalid_password_message_2);
            Toast.makeText(getApplicationContext(), invalidPasswordMessage, Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor prefsEditor = getPreferences(MODE_PRIVATE).edit();
            prefsEditor.putString(getString(R.string.user_email_key), ((EditText) findViewById(R.id.editTextEmailField)).getText().toString());
            prefsEditor.putString(getString(R.string.user_password_key), ((EditText) findViewById(R.id.editTextPasswordField)).getText().toString());
            prefsEditor.apply();
            startActivity(new Intent(getApplicationContext(), LandingScreen.class));
            finish();
        }
    }
}
