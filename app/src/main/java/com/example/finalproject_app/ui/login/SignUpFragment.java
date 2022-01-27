package com.example.finalproject_app.ui.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.finalproject_app.R;


public class SignUpFragment extends Fragment {

    EditText mailET;
    EditText passwordET;
    EditText verifyPasswordET;
    EditText nameET;
    EditText phoneET;
    Button signUpSaveBtn;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_sign_up, container, false);

        mailET=view.findViewById(R.id.signup_email);
        passwordET=view.findViewById(R.id.signup_password);
        nameET=view.findViewById(R.id.signup_name);
        phoneET=view.findViewById(R.id.signup_phone);
        verifyPasswordET=view.findViewById(R.id.signup_verify_password);
        progressBar=view.findViewById(R.id.signup_progressBar);
        signUpSaveBtn=view.findViewById(R.id.signup_btn);


        signUpSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                if(verifyFields())
//                {
//                    if(verifyPassword(view))
//                        saveUser(view);
//                }
            }
        });




        return view;
    }
}