package com.example.finalproject_app;


import androidx.appcompat.app.AppCompatActivity;
import static com.example.finalproject_app.LoginActivity.USER_ID;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    public static String USER_ID_sign;
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
    private TextView back_btn;
    //http
    private static final String ADD_USER_URL = "https://final-project-fastq.herokuapp.com/users/addUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup_btn=findViewById(R.id.signup_btn);
        signup_ID=findViewById(R.id.signup_UserID);
        signup_fullName=findViewById(R.id.signup_fullName);
        signup_email=findViewById(R.id.signup_email);
        signup_age=findViewById(R.id.signup_age);
        signup_height=findViewById(R.id.signup_height);
        signup_password=findViewById(R.id.signup_password);
        signup_verify_password=findViewById(R.id.signup_verify_password);
        back_btn=findViewById(R.id.signup_back_btn);



        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                v.getContext().startActivity(intent);


            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyFields(v) && matchPassword(v)) {
                    HttpCall httpCallPost = new HttpCall();
                    httpCallPost.setMethodtype(HttpCall.POST);
                    httpCallPost.setUrl(ADD_USER_URL);
                    HashMap<String, String> paramsPost = new HashMap<>();
                    Editable userID = signup_ID.getText();
                    Editable fullName = signup_fullName.getText();
                    Editable email = signup_email.getText();
                    Editable age = signup_age.getText();
                    Editable height = signup_height.getText();
                    Editable password = signup_password.getText();
                    paramsPost.put("userID", String.valueOf(userID));
                    paramsPost.put("fullName", String.valueOf(fullName));
                    paramsPost.put("email", String.valueOf(email));
                    paramsPost.put("age", String.valueOf(age));
                    paramsPost.put("height", String.valueOf(height));
                    paramsPost.put("password", String.valueOf(password));
                    httpCallPost.setParams(paramsPost);

                    new HttpRequest() {
                        String s;

                        @Override
                        public void onResponse(String response) {
                            super.onResponse(response);
                            s = response;
                            Log.d("response signup", "" + response);
                            try {
                                JSONObject obj = new JSONObject(s);
                                USER_ID_sign = obj.getString("userID");
                                Log.d("try userid sign", "" + USER_ID_sign);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.d("signup", "s:" + s);
                            if (!s.equals("An error POST Occurred!")) {
                                Log.d("usrtid sign", USER_ID_sign);
                                Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.execute(httpCallPost);
                }
            }
        });
    }







    public boolean isETEmpty(EditText et){
        if( et.getText().toString().equals("") || et.getText().toString().equals(" "))
        {
            et.setError("required");
            return true;
        }
        return false;
    }
    private boolean verifyFields(View v) {
        if(!isETEmpty(signup_ID) && !isETEmpty(signup_fullName)&& !isETEmpty(signup_email)&& !isETEmpty(signup_age)&& !isETEmpty(signup_height)&& !isETEmpty(signup_password)&& !isETEmpty(signup_verify_password) )
        {
            if(verifyPassword(v))
                return true;
        }
        return false;
    }
    private boolean verifyPassword(View v) {
        String p=signup_password.getText().toString();
        if(p.length()<6) {

            Toast toast = Toast.makeText(v.getContext(), "the password is at least 6 character", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        return true;
    }

    private boolean matchPassword(View v) {
        String p=signup_password.getText().toString();
        String p2=signup_verify_password.getText().toString();
        if(!p2.equals(p)) {

            Toast toast = Toast.makeText(v.getContext(), "the passwords not match", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        return true;
    }




}