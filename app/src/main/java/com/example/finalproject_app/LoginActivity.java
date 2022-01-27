package com.example.finalproject_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject_app.ui.login.SignUpFragment;

public class LoginActivity extends AppCompatActivity  {
    EditText mailET;
    EditText passwordET;
    Button loginBtn;
    Button signUpBtn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mailET=findViewById(R.id.login_email);
        passwordET=findViewById(R.id.login_password);
        progressBar=findViewById(R.id.login_progressBar);
        loginBtn=findViewById(R.id.login_loginbtn);
        signUpBtn=findViewById(R.id.login_sigup_btn);
       progressBar.setVisibility(View.INVISIBLE);//הוספתי


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verifyFields(view))
                {
//                    LoginUser(view);
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(intent);
                    Log.d("try","login try");
                }
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                Intent intent = new Intent(view.getContext(), SignUpActivity.class);
//                view.getContext().startActivity(intent);

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
//                SignUpFragment fragment1=new SignUpFragment();
//                FragmentManager manager=getSupportFragmentManager();
//                FragmentTransaction tran=manager.beginTransaction();
//                tran.replace(R.id.loginactivity_frame, fragment1).addToBackStack(LoginActivity.class.getSimpleName());
//                tran.commit();

//                SignUpFragment fragment = new SignUpFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.loginactivity_frame,fragment).commit().remove(this);
                Log.d("try","signup btn");
//                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
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
        if(!isETEmpty(mailET) && !isETEmpty(passwordET) )
        {
            if(verifyPassword(view))
                return true;
        }
        return false;
    }


    private boolean verifyPassword(View view) {
        String p=passwordET.getText().toString();
        if(p.length()<6) {
            Toast toast = Toast.makeText(view.getContext(), "the password is at least 6 character", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }



}