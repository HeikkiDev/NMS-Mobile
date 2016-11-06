package com.heikkidev.nomansskymobileapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen_Activity extends AppCompatActivity {

    //Duration of the splash screen in milliseconds
    private static final int SPLASH_DURATION_MS = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Animation that moves the logo from the right to the center
        Animation animationLogo = AnimationUtils.loadAnimation(SplashScreen_Activity.this, R.anim.splash_logo_move);

        //Animation that moves subtitle from the left to the center
        Animation animationText = AnimationUtils.loadAnimation(SplashScreen_Activity.this, R.anim.splash_subtitle_move);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        TextView txtMobileVersion = (TextView) findViewById(R.id.txtMobileVersion);
        if (imgLogo != null) {
            imgLogo.setAnimation(animationLogo);
        }
        if(txtMobileVersion != null){
            txtMobileVersion.setAnimation(animationText);
        }

        /**
         * The object waits X milliseconds before starting the other Activity
         * @see Handler
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen_Activity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DURATION_MS);
    }
}
