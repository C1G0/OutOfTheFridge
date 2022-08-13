package com.wandika.outofthefridge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    //Inisiasi Variabel
    Context mContext;
    ArrayList<ApiRequest> mData;

    public Adapter(Context mContext, ArrayList<ApiRequest> mData)
    {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.img.setImage();
        //holder.label.setText(mData.get(position).getLabel());
        //holder.calories.setText(mData.get(position).getCalories());
        //holder.time.setText(mData.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Inisialisai Variabel
        TextView label, calories, time;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.label_recipe);
            calories = itemView.findViewById(R.id.calories_recipe);
            time = itemView.findViewById(R.id.time_recipe);
            img = itemView.findViewById(R.id.img_recipe);
        }
    }
}
