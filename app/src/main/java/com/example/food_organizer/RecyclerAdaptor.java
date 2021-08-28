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

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder> {
    private static final String tag = "Recycler View";
    private Context imgContext;
    private ArrayList<Images> imgList;

    public RecyclerAdaptor(Context imgContext, ArrayList<Images> imgList) {
        this.imgContext = imgContext;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public RecyclerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view,
                parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // to set image text
        holder.textView.setText(imgList.get(position).getImageName());

        // to set image
        Glide.with(imgContext).load(imgList.get(position).getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewHome);
            textView = itemView.findViewById(R.id.textViewHome);

        }
    }
}
