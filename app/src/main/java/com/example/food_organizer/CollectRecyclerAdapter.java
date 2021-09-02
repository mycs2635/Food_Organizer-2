package com.example.food_organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CollectRecyclerAdapter extends RecyclerView.Adapter<CollectRecyclerAdapter.ViewHolder>{
    private static final String tag = "Recycler View";
    private Context collectContext;
    private ArrayList<Donationdetails> foodList;


    public CollectRecyclerAdapter(Context collectContext, ArrayList<Donationdetails> foodList) {
        this.collectContext = collectContext;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public CollectRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_details,
                parent,
                false);
        return new CollectRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectRecyclerAdapter.ViewHolder holder, int position) {
        // to set image text
        holder.description.setText(foodList.get(position).getDes());
        holder.address.setText(foodList.get(position).getAddress());
        holder.phone.setText(foodList.get(position).getPhone());

        // to set image
//        Glide.with(holder.imageView.getContext()).load(foodList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView address,description,phone;

        public ViewHolder(View itemView){
            super(itemView);

            address= itemView.findViewById(R.id.food_address);
            description= itemView.findViewById(R.id.food_description);
            phone= itemView.findViewById(R.id.food_phone);

        }
    }
}
