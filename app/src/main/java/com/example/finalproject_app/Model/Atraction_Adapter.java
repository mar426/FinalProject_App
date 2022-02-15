package com.example.finalproject_app.Model;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_app.R;
import com.example.finalproject_app.ui.home.AttractionsDetailsFragment;
import com.example.finalproject_app.ui.home.HomeFragment;
import com.example.finalproject_app.ui.home.HomeFragmentDirections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Atraction_Adapter extends RecyclerView.Adapter<Atraction_Adapter.AttractionViewHolder> {


    public String attraction_id; //for safe args
    private static List<String> clickPosition = new ArrayList<String>();
    private ArrayList<Attraction> attractions;
    public static List<String> selected_attractions= new ArrayList<String>() ;

    //constructor
    public Atraction_Adapter(ArrayList<Attraction> attractions) {
        this.attractions = attractions;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View attractionView= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_row,parent,false);
        return new AttractionViewHolder(attractionView);
    }




    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Attraction currentAttraction = attractions.get(position);
        holder.att_name.setText(currentAttraction.getAttraction_name());
//        Log.d("holder","image"+currentAttraction.getAttraction_image());
        holder.att_image.setImageResource(currentAttraction.getAttraction_image());
        holder.position = position;
        holder.att = currentAttraction;
        holder.add_attraction_btn.setVisibility(View.VISIBLE);
        //holder.row_index = -1;


        // ADD BTN
        holder.add_attraction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.row_index = position;
                if (currentAttraction.getStatus() == false) {
                    clickPosition.add(String.valueOf(position));
                    currentAttraction.setStatus(true);
                } else {

                    clickPosition.remove(String.valueOf(position));
                    selected_attractions.remove(String.valueOf(position + 1));
                    currentAttraction.setStatus(false);
                }

                notifyDataSetChanged();
            }
        });

        if (clickPosition.contains(String.valueOf(position))) {
            Log.d("not string", "--" + currentAttraction.getAttraction_id());
            Log.d("string", "--" + String.valueOf(currentAttraction.getAttraction_id()));
            if (selected_attractions.contains(String.valueOf(currentAttraction.getAttraction_id()))) {
                Log.d("click", "exits ");
            } else {
                selected_attractions.add(String.valueOf(currentAttraction.getAttraction_id()));
                Log.d("click", "add " + currentAttraction.getAttraction_name() + " to route ");
            }
            //holder.add_attraction_btn.setTag(currentAttraction.getStatus());
            Log.d("add_attraction_btn", "status:" + currentAttraction.getStatus());

            //notifyDataSetChanged();
            //holder.add_attraction_btn.setVisibility(View.INVISIBLE);
        }
        //&& String.valueOf(holder.add_attraction_btn.getTag())=="false"
        if (currentAttraction.getStatus() == false) {
            holder.add_attraction_btn.setText("Add");
            holder.add_attraction_btn.setBackgroundColor(Color.BLUE);
        } else {
            holder.add_attraction_btn.setText("Remove");
            holder.add_attraction_btn.setBackgroundColor(Color.RED);
        }

        // DETAILS BTN
        holder.details_attraction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attraction_id=String.valueOf(position);
//                Navigation.findNavController(v).navigate(R.id.action_HomeFragment_to_attractionsDetailsFragment);
                HomeFragmentDirections.ActionHomeFragmentToAttractionsDetailsFragment action = HomeFragmentDirections.actionHomeFragmentToAttractionsDetailsFragment(attraction_id);
                Navigation.findNavController(v).navigate((NavDirections) action);
            }
        });



    }




    @Override
    public int getItemCount() {
        return attractions.size();
    }


    //View Holder
    public static class AttractionViewHolder extends RecyclerView.ViewHolder{

        public TextView att_name;
        public ImageView att_image;
        public Button add_attraction_btn;
        public Button details_attraction_btn;
        public int position;
        public Attraction att;
        public int row_index;


        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
//            attraction_img=itemView.findViewById(R.id.home_listrow_image);
            att_name = itemView.findViewById(R.id.home_listrow_atraction_name);
            add_attraction_btn = itemView.findViewById(R.id.add_attraction);
            details_attraction_btn = itemView.findViewById(R.id.details_attraction);
            att_image=itemView.findViewById(R.id.home_listrow_image);
//
        }
    }








}