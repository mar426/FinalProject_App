package com.example.finalproject_app.ui.Schedule;


import static com.example.finalproject_app.LoginActivity.USER_ID;
import static com.example.finalproject_app.SignUpActivity.USER_ID_sign;
import static com.example.finalproject_app.ui.Profile.ProfileFragment.temp;
import static com.example.finalproject_app.ui.home.HomeFragment.images;
import static com.example.finalproject_app.ui.home.HomeFragment.att_names;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.example.finalproject_app.R;
import com.example.finalproject_app.databinding.FragmentDashboardBinding;
import com.example.finalproject_app.ui.home.HomeFragment;

import static com.example.finalproject_app.LoginActivity.FLAG;

import static java.lang.Integer.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class ScheduleFragment extends Fragment {

    private static final String SERVER_GET_ROUTE= "https://final-project-fastq.herokuapp.com/users/getRoute";
    private ScheduleViewModel scheduleViewModel;
    private FragmentDashboardBinding binding;
    private ImageView attractionImage;
    public static JSONArray times;
    public static JSONArray attractions;
    public ArrayList attractionArray = new ArrayList();
    public ArrayList timesArray = new ArrayList();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        View view = inflater.inflate(R.layout.before_schedule_fragment, container, false);



        //http request:
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.POST);
        httpCall.setUrl(SERVER_GET_ROUTE);
        HashMap<String,String> params = new HashMap<>();
        if(USER_ID!=null)
            params.put("userID", USER_ID);
        else
            params.put("userID", USER_ID_sign);
        Log.d("user id","scedule "+params);
        httpCall.setParams(params);
        new HttpRequest(){
            String s;
            @Override
            public void onResponse(String response) {
                s = response;
                ArrayList schedule = new ArrayList();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.has("flag")) {
                        FLAG = obj.getString("flag");
                        Log.d("schedule", "flag "+FLAG);
                    }
                    else {
                        JSONObject arrayObj = new JSONObject(s);
                        times = arrayObj.getJSONArray("times");
                        attractions = arrayObj.getJSONArray("attractions");
                        Log.d("attractions:", "" + attractions.length());
                        Log.d("times:", "" + times);
                        Log.d("att:", "" + attractions.getString(0));
                        Log.d("time:", "" + times.getString(0));

                        FLAG = "1";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute(httpCall);



        if(!FLAG.equals("0")){

            for (int i = 0; i<times.length(); i++)
            {
                try {
                    timesArray.add(times.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i<attractions.length(); i++)
            {
                try {
                    attractionArray.add(attractions.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d("attractions:", "" + attractionArray);
            Log.d("times:", "" + timesArray.get(0).getClass());

            Log.d("schedule---------", "flag if" + FLAG);
            view = inflater.inflate(R.layout.fragment_schedule, container, false);
            //LIST ADAPTER
            ListView schedule_list= view.findViewById(R.id.schedule_listView);
            MyAdapter myAdapter = new MyAdapter();
            schedule_list.setAdapter(myAdapter);
        }
        else{
            Log.d("schedule", "else" + FLAG);
        }



//        scheduleViewModel =
//                new ViewModelProvider(this).get(ScheduleViewModel.class);
//
//        binding = FragmentDashboardBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textDashboard;
//        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    // adapter of list view

    class MyAdapter extends BaseAdapter {

        LinkedList<String> dataAttractions;
        LinkedList<String> dataTimes;

        //constractor
        MyAdapter() {
            dataAttractions = new LinkedList<String>();
            dataTimes = new LinkedList<String>();
            for (int i = 0; i < attractionArray.size(); ++i) {
                dataAttractions.add(attractionArray.get(i).toString());
                dataTimes.add(timesArray.get(i).toString());
            }
        }

        @Override
        public int getCount() {
            return dataAttractions.size();
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position /*row index*/, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater1 = getLayoutInflater();
                convertView = inflater1.inflate(R.layout.schedule_list_row, null);

            }
            TextView attraction_name = convertView.findViewById(R.id.schedule_listrow_atraction_name);
            TextView enter_time=convertView.findViewById(R.id.schedule_listrow_enterTime);
            ImageView imageAttraction = convertView.findViewById(R.id.schedule_listrow_image);
            attraction_name.setText(att_names[Integer.parseInt(dataAttractions.get(position))-1]);
            enter_time.setText(dataTimes.get(position));
            imageAttraction.setImageResource(images[Integer.parseInt(dataAttractions.get(position))-1]);
            return convertView;
        }
    }

}