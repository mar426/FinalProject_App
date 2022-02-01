package com.example.finalproject_app.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_app.Model.Atraction_Adapter;
import com.example.finalproject_app.Model.Attraction;
import com.example.finalproject_app.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    Button route_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        ArrayList<Attraction> Attractions_arr= new ArrayList<>();
        for(int i=1; i<20; ++i){
            Attractions_arr.add(new Attraction("attraction "+i,"details",i));
        }


//        ListView list_home = view.findViewById(R.id.homeFragment_listView);
//
//        MyAdapter myAdapter = new MyAdapter();
//        list_home.setAdapter(myAdapter);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Atraction_Adapter adapter= new Atraction_Adapter(Attractions_arr);
        recyclerView.setAdapter(adapter);

        route_btn=view.findViewById(R.id.home_Route_btn);
        route_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( "try",""+adapter.selected_attractions);
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