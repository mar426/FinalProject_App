package com.example.finalproject_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    TextView appName;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appName=findViewById(R.id.splash_appName);
        lottie=findViewById(R.id.splash_lottie);

        appName.animate().translationY(-1400).setDuration(2700).setStartDelay(1200);
        lottie.animate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }




}

//    SignUpFragment fragment1=new SignUpFragment();
//    FragmentManager manager=getSupportFragmentManager();
//    FragmentTransaction tran=manager.beginTransaction();
//                tran.add(R.id.loginactivity_frame, fragment1);
//                        tran.commit();