package com.example.finalproject_app.ui.Profile;

import static com.example.finalproject_app.LoginActivity.USER_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.example.finalproject_app.R;
import com.example.finalproject_app.databinding.FragmentNotificationsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentNotificationsBinding binding;
    private static final String SERVER = "http://10.0.2.2:3000/users/getUserByID";
    private TextView name;
    private TextView email;
    private TextView password;
    private TextView age;
    private TextView height;
    public static String temp;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //find elements from xml:
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        email = view.findViewById(R.id.user_profile_mail);
        password = view.findViewById(R.id.user_profile_password);
        age = view.findViewById(R.id.user_profile_age);
        height = view.findViewById(R.id.user_profile_height);
        name = view.findViewById(R.id.user_profile_name);

        //http request:
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.POST);
        httpCall.setUrl(SERVER);
        HashMap<String,String> params = new HashMap<>();
        params.put("userID", USER_ID);
        httpCall.setParams(params);

        new HttpRequest(){
            String s;
            String name_json;
            String email_jsom;
            String phone_json;
            String password_json;
            String age_json;
            String height_json;
            @Override
            public void onResponse(String response) {
                s = response;
                try {
                    JSONObject obj = new JSONObject(s);
                    name_json = obj.getString("fullName");
                    email_jsom =  obj.getString("email");
                    phone_json =  obj.getString("userID");
                    password_json =  obj.getString("password");
                    age_json =  obj.getString("age");
                    height_json =  obj.getString("height");
                    temp = obj.getString("email");

                    //String selected_attractions =  objA.getString("selected_attractions");
                    //Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                name.setText(name_json);
                email.setText(email_jsom);
                password.setText(password_json);
                age.setText(age_json);
                height.setText(height_json);
            }
        }.execute(httpCall);

        return view;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}