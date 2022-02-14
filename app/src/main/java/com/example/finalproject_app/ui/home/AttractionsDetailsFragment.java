package com.example.finalproject_app.ui.home;

import static com.example.finalproject_app.ui.home.HomeFragment.Attractions_arr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject_app.Model.Attraction;
import com.example.finalproject_app.R;

public class AttractionsDetailsFragment extends Fragment {

    private TextView att_name;
    private TextView att_description;
    private TextView att_age;
    private TextView att_height;
    private TextView att_round;
    private ImageView att_image;
    public Attraction attractionNOW;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attractions_details, container, false);

        String att_pos= AttractionsDetailsFragmentArgs.fromBundle(getArguments()).getAttractionId();

        attractionNOW =Attractions_arr.get(Integer.parseInt(att_pos));
        Log.d("det frag","att id "+attractionNOW.getAttraction_id());

        att_name=view.findViewById(R.id.deatailsFrag_attraction_name);
        att_description=view.findViewById(R.id.deatailsFrag_attraction_description);
        att_age=view.findViewById(R.id.deatailsFrag_age);
        att_height=view.findViewById(R.id.deatailsFrag_height);
        att_round=view.findViewById(R.id.deatailsFrag_roundtime);
        att_image=view.findViewById(R.id.deatailsFrag_attraction_image);


        att_name.setText(attractionNOW.getAttraction_name());
        att_description.setText(attractionNOW.getAttraction_description());
        att_age.setText(String.valueOf(attractionNOW.getAge()));
        att_height.setText(String.valueOf(attractionNOW.getHeight()));
        att_round.setText(String.valueOf(attractionNOW.getRound()));
        att_image.setImageResource(attractionNOW.getAttraction_image());


        return  view;
    }
}