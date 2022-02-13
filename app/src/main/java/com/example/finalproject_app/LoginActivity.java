package com.example.finalproject_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.example.finalproject_app.ui.login.SignUpFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity  {
    private static final String SERVER = "http://10.0.2.2:3000/users/login";
    EditText login_email;
    EditText login_password;
    Button login_loginbtn;
    Button login_sigupbtn;
    ProgressBar progressBar;
    public static String USER_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        progressBar=findViewById(R.id.login_progressBar);
        login_loginbtn=findViewById(R.id.login_loginbtn);
        login_sigupbtn=findViewById(R.id.login_sigup_btn);
        progressBar.setVisibility(View.INVISIBLE);


        login_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpCall httpCall = new HttpCall();
                httpCall.setMethodtype(HttpCall.POST);
                httpCall.setUrl(SERVER);
                HashMap<String,String> params = new HashMap<>();
                Editable password = login_password.getText();
                Editable email = login_email.getText();
                params.put("password", String.valueOf(password));
                params.put("email", String.valueOf(email));
                httpCall.setParams(params);
                new HttpRequest(){
                    String s;
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        Log.d("GET", response);
                        s = response;
                        try {
                            JSONObject obj = new JSONObject(s);
                            USER_ID = obj.getString("userID");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        Log.d("LOGIN", s);
                        if(verifyFields(view))
                        {
                            Log.d("LOGIN","s:"+s);
                            if (!s.equals("Invalid password") && !s.equals("Invalid username") ) {
                                Log.d("LOGIN111", s);
                                //                    LoginUser(view);
                                Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(view.getContext(), MainActivity.class);
                                view.getContext().startActivity(intent);
                                Log.d("try", "login try");
                            }
                            else {
                                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }.execute(httpCall);


            }
        });


        login_sigupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

    }



// functions

//    private void LoginUser(View view) {
//        String email,password;
//        email=mailET.getText().toString();
//        password=passwordET.getText().toString();
//        loginBtn.setEnabled(false);
//        signUpBtn.setEnabled(false);
//        progressBar.setVisibility(View.VISIBLE);
//        Model.instance.LoginUser(email, password, new Model.LoginUserListener() {
//            @Override
//            public void onComplete(boolean success) {
//                if(success)
//                {
//                    Toast.makeText(view.getContext(),"user login",Toast.LENGTH_SHORT).show();
//                    final String id=Model.instance.getUserID();
////                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_nav_home);
//                }
//                else {
//                    Toast.makeText(view.getContext(),"ERROR",Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.INVISIBLE);
//                    loginBtn.setEnabled(true);
//                    signUpBtn.setEnabled(true);
//                }
//            }
//        });
//    }

    public boolean isETEmpty(EditText et){
        if( et.getText().toString().equals("") || et.getText().toString().equals(" "))
        {
            et.setError("required");
            return true;
        }
        return false;
    }
    private boolean verifyFields(View view) {
        if(!isETEmpty(login_email) && !isETEmpty(login_password) )
        {
            if(verifyPassword(view))
                return true;
        }
        return false;
    }
    private boolean verifyPassword(View view) {
        String p=login_password.getText().toString();
        if(p.length()<6) {
            Toast toast = Toast.makeText(view.getContext(), "the password is at least 6 character", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }



}