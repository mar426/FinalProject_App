package com.example.finalproject_app.ui.Profile;

import static com.example.finalproject_app.SignUpActivity.USER_ID_sign;
import static com.example.finalproject_app.LoginActivity.USER_ID;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.util.Log;
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

import org.json.JSONObject;

import java.util.HashMap;


public class editProfileFragment extends Fragment {

    //http
    private static final String ADD_USER_URL = "https://final-project-fastq.herokuapp.com/users/updateUser";
    private static final String SERVER = "https://final-project-fastq.herokuapp.com/users/getUserByID";
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

        //http request:
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.POST);
        httpCall.setUrl(SERVER);
        HashMap<String,String> params = new HashMap<>();
        if(USER_ID!=null)
            params.put("userID", USER_ID);
        else
            params.put("userID", USER_ID_sign);
        Log.d("user id","user id sign "+USER_ID_sign);
        Log.d("user id","profile "+params);
        httpCall.setParams(params);
        new HttpRequest(){
            @Override
            public void onResponse(String response) {
                String s;
                String name_json = null;
                String password_json = null;
                String age_json = null;
                String height_json = null;

                s = response;

                try {
                    JSONObject obj = new JSONObject(s);
                    name_json = obj.getString("fullName");
                    password_json = obj.getString("password");
                    age_json = obj.getString("age");
                    height_json = obj.getString("height");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                EditProfile_name.setText(name_json);
                EditProfile_password.setText(password_json);
                EditProfile_age.setText(age_json);
                EditProfile_height.setText(height_json);
            }
        }.execute(httpCall);



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
                if(USER_ID!=null)
                    paramsPost.put("userID", String.valueOf(USER_ID));
                else
                    paramsPost.put("userID", String.valueOf(USER_ID_sign));
                Log.d("user id","edit profile "+paramsPost);
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