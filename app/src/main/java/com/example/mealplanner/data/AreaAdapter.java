package com.example.mealplanner.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Area;

import java.util.ArrayList;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder>{

    private ArrayList<Area> country;
    public AreaAdapter(final ArrayList<Area> country) {
        this.country= country;
    }
    @NonNull
    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.MyViewHolder holder, int position) {
        Area area = country.get(position);
        holder.image.setImageResource(area.getImage());
        holder.name.setText(area.getCountryName());

    }

    @Override
    public int getItemCount() {
        return country.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.countryTxt);
            image = itemView.findViewById(R.id.flagCountry);
            layout = itemView.findViewById(R.id.recycleViewArea);
        }
    }
}
