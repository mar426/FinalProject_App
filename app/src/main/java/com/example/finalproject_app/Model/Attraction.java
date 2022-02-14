package com.example.finalproject_app.Model;

import android.media.Image;
import android.widget.Button;
import android.widget.ImageView;

public class Attraction {
    private int attraction_id;
    private String attraction_name;
    private String attraction_description;
    private int age;
    private int height;
    private int round;
    private boolean status;
    private int attraction_image;


    //constructor


    public Attraction(int attraction_id, String attraction_name, String attraction_description, int age, int height, int round, boolean status, int attraction_image) {
        this.attraction_id = attraction_id;
        this.attraction_name = attraction_name;
        this.attraction_description = attraction_description;
        this.age = age;
        this.height = height;
        this.round = round;
        this.status = status;
        this.attraction_image = attraction_image;
    }

    public int getAttraction_id() {
        return attraction_id;
    }

    public String getAttraction_name() {
        return attraction_name;
    }

    public String getAttraction_description() {
        return attraction_description;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getRound() {
        return round;
    }

    public boolean getStatus() {
        return status;
    }

    public int getAttraction_image() {
        return attraction_image;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
