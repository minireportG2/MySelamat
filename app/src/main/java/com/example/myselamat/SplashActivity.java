package com.example.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    ImageView background;
    TextView myselamat_tv, slogan_tv;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        background = (ImageView) findViewById(R.id.background);
        myselamat_tv = (TextView) findViewById(R.id.myselamat_tv);
        slogan_tv = (TextView) findViewById(R.id.slogan_tv);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie);

        //Start after 5s and run for 1s
        background.animate().translationY(-2500).setDuration(1000).setStartDelay(5000);
        myselamat_tv.animate().translationY(3000).setDuration(1000).setStartDelay(5000);
        slogan_tv.animate().translationY(2000).setDuration(1000).setStartDelay(5000);
        lottieAnimationView.animate().translationY(1500).setDuration(1000).setStartDelay(5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 6000); //Total 6s

    }
}