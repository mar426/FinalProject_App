package com.example.finalproject_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 2500);
    }




}

//    SignUpFragment fragment1=new SignUpFragment();
//    FragmentManager manager=getSupportFragmentManager();
//    FragmentTransaction tran=manager.beginTransaction();
//                tran.add(R.id.loginactivity_frame, fragment1);
//                        tran.commit();