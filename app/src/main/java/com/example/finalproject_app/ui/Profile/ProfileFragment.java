package com.example.finalproject_app.ui.Profile;

import static com.example.finalproject_app.LoginActivity.USER_ID;

import static com.example.finalproject_app.SignUpActivity.USER_ID_sign;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.example.finalproject_app.R;
import com.example.finalproject_app.databinding.FragmentNotificationsBinding;

import org.json.JSONObject;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentNotificationsBinding binding;
    private static final String SERVER ="https://final-project-fastq.herokuapp.com/users/getUserByID";
    private TextView name;
    private TextView email;
    private TextView password;
    private TextView age;
    private TextView height;
    private ImageView editBtn;
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
        editBtn=view.findViewById(R.id.user_profile_edit_btn);

        //http request:
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.POST);
        httpCall.setUrl(SERVER);
        HashMap<String,String> params = new HashMap<>();
        if(USER_ID!=null)
            params.put("userID", USER_ID);
        else if(USER_ID_sign!=null)
            params.put("userID", USER_ID_sign);
        Log.d("user id","user id sign "+USER_ID_sign);
        Log.d("user id","user id login "+USER_ID);
        Log.d("user id","profile "+params);
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

        editBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_notifications_to_editProfileFragment2));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}