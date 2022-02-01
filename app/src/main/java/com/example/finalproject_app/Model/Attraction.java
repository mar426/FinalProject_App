package com.example.finalproject_app.Model;

import android.media.Image;
import android.widget.Button;
import android.widget.ImageView;

public class Attraction {
    private String attraction_name;
    private String attraction_details;
    private int attraction_id;


    //constructor


    public Attraction(String attraction_name, String attraction_details, int attraction_id) {
        this.attraction_name = attraction_name;
        this.attraction_details = attraction_details;
        this.attraction_id = attraction_id;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public String getAttraction_details() {
        return attraction_details;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

}
