package com.example.finalproject_app.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject_app.R;

public class AttractionsDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attractions_details, container, false);

        String attraction_id= AttractionsDetailsFragmentArgs.fromBundle(getArguments()).getAttractionId();
        Log.d("args","attr id"+attraction_id);


        return  view;
    }
}