package com.example.finalproject_app.ui.Schedule;

import static com.example.finalproject_app.LoginActivity.USER_ID;
import static com.example.finalproject_app.ui.Profile.ProfileFragment.temp;

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
import static com.example.finalproject_app.LoginActivity.FLAG;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

public class ScheduleFragment extends Fragment {

    private static final String SERVER_GET_ROUTE = "http://10.0.2.2:3000/users/getRoute";
    private ScheduleViewModel scheduleViewModel;
    private FragmentDashboardBinding binding;
    private ImageView attractionImage;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
//        view = inflater.inflate(R.layout.before_schedule_fragment, container, false);


        //http request:
        HttpCall httpCall = new HttpCall();
        httpCall.setMethodtype(HttpCall.POST);
        httpCall.setUrl(SERVER_GET_ROUTE);
        HashMap<String,String> params = new HashMap<>();
        params.put("id", USER_ID);
        httpCall.setParams(params);
        new HttpRequest(){
            String s;
            @Override
            public void onResponse(String response) {
                s = response;
                try {
                    JSONObject obj = new JSONObject(s);
                    if(obj.has("flag")) {
                        FLAG = obj.getString("flag");
                        Log.d("schedule", "flag "+FLAG);
                    }
                    else {
                        FLAG = obj.getString("selected_attractions");
                        Log.d("schedule", "flag not 0 " + FLAG);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute(httpCall);



//        if(!FLAG.equals("0")){
//            Log.d("schedule", "flag if" + FLAG);
//            view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        }
//        else{
//            Log.d("schedule", "else" + FLAG);
//        }

        //LIST ADAPTER
        ListView schedule_list= view.findViewById(R.id.schedule_listView);
        MyAdapter myAdapter = new MyAdapter();
        schedule_list.setAdapter(myAdapter);

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

        LinkedList<String> data;

        //constractor
        MyAdapter() {
            data = new LinkedList<String>();
            for (int i = 0; i < 15; ++i) {
                data.add("attraction" + i);
            }
        }

        @Override
        public int getCount() {
            return data.size();
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
            attraction_name.setText(data.get(position));
            enter_time.setText("08:00");
            return convertView;
        }
    }

}