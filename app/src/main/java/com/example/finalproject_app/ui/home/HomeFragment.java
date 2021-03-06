package com.example.finalproject_app.ui.home;

import static com.example.finalproject_app.LoginActivity.HOME_FLAG;
import static com.example.finalproject_app.LoginActivity.ROUTE_FLAG;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_app.HTTP.HttpCall;
import com.example.finalproject_app.HTTP.HttpRequest;
import com.example.finalproject_app.MainActivity;
import com.example.finalproject_app.Model.Atraction_Adapter;
import com.example.finalproject_app.Model.Attraction;
import com.example.finalproject_app.R;
import com.example.finalproject_app.SignUpActivity;

import static com.example.finalproject_app.LoginActivity.USER_ID;
import static com.example.finalproject_app.SignUpActivity.USER_ID_sign;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
// test

    private RecyclerView recyclerView;
    private Button route_btn;
    private static final String UPDATE_SA_USER_URL = "https://final-project-fastq.herokuapp.com/users/selected_attraction_update";
    public static String att_names[],att_description[];
    public int att_height[],att_age[],att_round[];
    public static ArrayList<Attraction> Attractions_arr= new ArrayList<>();



    public static int images[]={R.drawable.train,R.drawable.images,R.drawable.pirateship, R.drawable.bumpercars,
            R.drawable.water, R.drawable.topspin,R.drawable.theking,R.drawable.carousel, R.drawable.swing,R.drawable.blackmamba};



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        att_names=getResources().getStringArray(R.array.attractions_name);
        att_description=getResources().getStringArray(R.array.attractions_description);
        att_height=getResources().getIntArray(R.array.attractions_height);
        att_age=getResources().getIntArray(R.array.attractions_age);
        att_round=getResources().getIntArray(R.array.attractions_round);
        route_btn=view.findViewById(R.id.home_Route_btn);

        if(ROUTE_FLAG==1)
            route_btn.setVisibility(View.INVISIBLE);

        if(HOME_FLAG==0) {
            for (int i = 0; i < 10; ++i) {
                Attractions_arr.add(new Attraction((i + 1), att_names[i], att_description[i], att_age[i], att_height[i], att_round[i], false, images[i]));

            }
        }

        HOME_FLAG = 1;



//LIST ADAPTER
//        ListView list_home = view.findViewById(R.id.homeFragment_listView);
//        MyAdapter myAdapter = new MyAdapter();
//        list_home.setAdapter(myAdapter);



        recyclerView = view.findViewById(R.id.home_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Atraction_Adapter adapter= new Atraction_Adapter(Attractions_arr);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        route_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpCall httpCallPost = new HttpCall();
                httpCallPost.setMethodtype(HttpCall.POST);
                httpCallPost.setUrl(UPDATE_SA_USER_URL);
                HashMap<String,String> paramsPost = new HashMap<>();

                for(int i = 0;i<adapter.selected_attractions.size();i++)
                {
                    paramsPost.put("selected_attractions"+"["+i+"]", String.valueOf((adapter.selected_attractions).get(i)));
                }
                if(USER_ID!=null)
                    paramsPost.put("userID", USER_ID);
                else
                    paramsPost.put("userID", USER_ID_sign);
                Log.d("user id","home "+paramsPost);
                httpCallPost.setParams(paramsPost);
                new HttpRequest(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        String s = response;
                        Log.d("UPDATE", s);
                        try {
                            Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        if (s.equals("User added successfully")) {
//                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
//                        }
                    }
                }.execute(httpCallPost);
                Log.d( "try",""+adapter.selected_attractions);
                Log.d( "try",""+paramsPost);
                ROUTE_FLAG = 1;
                route_btn.setVisibility(View.INVISIBLE);
            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.nav_view).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Attractions_arr.clear();

    }




    // adapter of list view (we are use on recyclerview)

//    class MyAdapter extends BaseAdapter {
//
//        List<String> data;
//
//        //constractor
//        MyAdapter() {
//            data = new LinkedList<String>();
//            for (int i = 0; i < 15; ++i) {
//                data.add("attraction" + i);
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return data.size();
//        }
//
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//
//        @Override
//        public View getView(int position /*row index*/, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                LayoutInflater inflater1 = getLayoutInflater();
//                convertView = inflater1.inflate(R.layout.home_list_row, null);
//
//            }
//            TextView attraction_name = convertView.findViewById(R.id.home_listrow_atraction_name);
//            attraction_name.setText(data.get(position));
//            return convertView;
//        }
//    }


}