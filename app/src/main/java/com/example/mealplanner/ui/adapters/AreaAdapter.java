package com.example.mealplanner.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Area;
import com.example.mealplanner.ui.fragments.CountriesFragmentDirections;

import java.util.ArrayList;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.MyViewHolder>{

    private ArrayList<Area> country;

    private View view;

    public AreaAdapter(final ArrayList<Area> country) {
        this.country= country;
    }
    @NonNull
    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.view=parent;

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.MyViewHolder holder, int position) {
        Area area = country.get(position);
        String name = country.get(position).getName();
        holder.image.setImageResource(area.getImage());
        holder.name.setText(area.getName());

        holder.itemView.setOnClickListener((view1)->{
           // int pos = getAdapterPosition();
            //if(pos != RecyclerView.NO_POSITION) {
                NavDirections directions =
                        CountriesFragmentDirections.actionCountriesFragmentToMealsForSpecificAreaFragment(country.get(position).getName());
                Navigation.findNavController(view).navigate(directions);
                // onCountryClick.onClickCountry(name);

        });

    }

    @Override
    public int getItemCount() {
        return country.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;
        RelativeLayout layout;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.countryTxt);
            image = itemView.findViewById(R.id.flagCountry);
            layout = itemView.findViewById(R.id.recycleViewArea);

            constraintLayout = itemView.findViewById(R.id.area_layout);


        }
    }
}
