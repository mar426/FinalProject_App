package com.example.finalproject_app.ui.Profile;

import static com.example.finalproject_app.LoginActivity.USER_ID;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.example.finalproject_app.MainActivity;
import com.example.finalproject_app.R;
import com.example.finalproject_app.SignUpActivity;

import java.util.HashMap;


public class editProfileFragment extends Fragment {

    //http
    private static final String ADD_USER_URL = "http://10.0.2.2:3000/users/updateUser";

    EditText EditProfile_name;
    EditText EditProfile_password;
    EditText EditProfile_age;
    EditText EditProfile_height;
    Button EditProfile_saveBtn;
    Button EditProfile_cancelBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        //find elements:
        EditProfile_name = view.findViewById(R.id.edit_profile_name);
        EditProfile_password = view.findViewById(R.id.edit_profile_password_ET);
        EditProfile_age = view.findViewById(R.id.edit_profile_age);
        EditProfile_height = view.findViewById(R.id.edit_profile_height);
        EditProfile_saveBtn = view.findViewById(R.id.edit_profile_save_button);
        EditProfile_cancelBtn = view.findViewById(R.id.edit_profile_cancel_button);

        //save button
        EditProfile_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall httpCallPost = new HttpCall();
                httpCallPost.setMethodtype(HttpCall.POST);
                httpCallPost.setUrl(ADD_USER_URL);
                HashMap<String,String> paramsPost = new HashMap<>();
                Editable fullName = EditProfile_name.getText();
                Editable password = EditProfile_password.getText();
                Editable age = EditProfile_age.getText();
                Editable height = EditProfile_height.getText();
                paramsPost.put("userID", String.valueOf(USER_ID));
                paramsPost.put("fullName", String.valueOf(fullName));
                paramsPost.put("password", String.valueOf(password));
                paramsPost.put("age", String.valueOf(age));
                paramsPost.put("height", String.valueOf(height));
                httpCallPost.setParams(paramsPost);
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        String s = response;
                        try {
                            Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (s.equals("User Updated successfully")) {
                            Navigation.findNavController(view).popBackStack();
                            //startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        }
                    }
                }.execute(httpCallPost);
            }
        });

        //cancel button
        EditProfile_cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
        return view;
    }
}