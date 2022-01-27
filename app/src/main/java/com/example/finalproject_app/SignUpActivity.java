package com.example.finalproject_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Edit Texts
    private EditText signup_ID;
    private EditText signup_fullName;
    private EditText signup_email;
    private EditText signup_age;
    private EditText signup_height;
    private EditText signup_password;
    private EditText signup_verify_password;
    //Buttons
    private Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup_btn=findViewById(R.id.signup_btn);
        signup_fullName=findViewById(R.id.signup_fullName);
        signup_email=findViewById(R.id.signup_email);
        signup_age=findViewById(R.id.signup_age);
        signup_height=findViewById(R.id.signup_height);
        signup_password=findViewById(R.id.signup_password);
        signup_verify_password=findViewById(R.id.signup_verify_password);



        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}