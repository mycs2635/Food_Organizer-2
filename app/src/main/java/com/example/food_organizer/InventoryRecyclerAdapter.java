package com.example.food_organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InventoryRecyclerAdapter extends RecyclerView.Adapter<InventoryRecyclerAdapter.ViewHolder>{
    private static final String tag = "Recycler View";
    private Context inventoryContext;
    private ArrayList<Products> itemList;


    public InventoryRecyclerAdapter(Context inventoryContext, ArrayList<Products> itemList) {
        this.inventoryContext = inventoryContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public InventoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_items,
                parent,
                false);
        return new InventoryRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryRecyclerAdapter.ViewHolder holder, int position) {
        // to set image text
        holder.name.setText(itemList.get(position).getProductName());
        holder.date.setText(itemList.get(position).getExDate());
        holder.place.setText(itemList.get(position).getPlace());

        // to set image
//        Glide.with(holder.imageView.getContext()).load(imgList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,date,place;

        public ViewHolder(View itemView){
            super(itemView);
               name=itemView.findViewById(R.id.item_name);
               date=itemView.findViewById(R.id.expire_date);
               place=itemView.findViewById(R.id.item_place);


        }
    }
}
