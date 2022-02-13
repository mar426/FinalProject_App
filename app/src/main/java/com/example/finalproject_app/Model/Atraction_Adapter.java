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
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Atraction_Adapter extends RecyclerView.Adapter<Atraction_Adapter.AttractionViewHolder> {

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
        Attraction currentAttraction= attractions.get(position);
        holder.attraction_name.setText(currentAttraction.getAttraction_name());
        holder.position=position;
        holder.att=currentAttraction;
        holder.add_attraction_btn.setVisibility(View.VISIBLE);
        //holder.row_index = -1;
        holder.add_attraction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.row_index = position;
                if (currentAttraction.getStatus()==false) {
                    clickPosition.add(String.valueOf(position));
                    currentAttraction.setStatus(true);
                }
                else {

                    clickPosition.remove(String.valueOf(position));
                    selected_attractions.remove(String.valueOf(position+1));
                    currentAttraction.setStatus(false);
                }

                notifyDataSetChanged();
            }
        });

        if(clickPosition.contains(String.valueOf(position))){
            Log.d("not string","--"+currentAttraction.getAttraction_id());
            Log.d("string","--"+String.valueOf(currentAttraction.getAttraction_id()));
            if(selected_attractions.contains(String.valueOf(currentAttraction.getAttraction_id()))){
                Log.d("click", "exits ");
            }
            else {
                selected_attractions.add(String.valueOf(currentAttraction.getAttraction_id()));
                Log.d("click", "add " +currentAttraction.getAttraction_name()+" to route ");
            }
            //holder.add_attraction_btn.setTag(currentAttraction.getStatus());
            Log.d("add_attraction_btn", "status:"+ currentAttraction.getStatus());

            //notifyDataSetChanged();
            //holder.add_attraction_btn.setVisibility(View.INVISIBLE);
        }
        //&& String.valueOf(holder.add_attraction_btn.getTag())=="false"
        if(currentAttraction.getStatus()==false){
            holder.add_attraction_btn.setText("Add");
            holder.add_attraction_btn.setBackgroundColor(Color.BLUE);
        }
        else{
            holder.add_attraction_btn.setText("Remove");
            holder.add_attraction_btn.setBackgroundColor(Color.RED);
        }

    }





    @Override
    public int getItemCount() {
        return attractions.size();
    }


    //View Holder
    public static class AttractionViewHolder extends RecyclerView.ViewHolder{
        //        public ImageView attraction_img;
        public TextView attraction_name;
        public Button add_attraction_btn;
        public Button details_attraction_btn;
        public int position;
        public Attraction att;
        public int row_index;


        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
//            attraction_img=itemView.findViewById(R.id.home_listrow_image);
            attraction_name = itemView.findViewById(R.id.home_listrow_atraction_name);
            add_attraction_btn = itemView.findViewById(R.id.add_attraction);
            details_attraction_btn = itemView.findViewById(R.id.details_attraction);


//            add_attraction_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//  selected_attractions.add(currentAttraction.getAttraction_id());
//                    Log.d("click", "add " +att.getAttraction_name()+" to route ");
//                }
//            });
//


            details_attraction_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Log.d("click", "deatails of " +att.getAttraction_name());
                    Log.d("click","selected attractions: "+selected_attractions);
                    Log.d("click","click positions: "+clickPosition);
                }
            });

        }
    }
}