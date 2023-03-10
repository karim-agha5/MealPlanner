package com.example.mealplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.onBoardingViewHolder> {

        List<OnboardingItem> pages;

        public ImageAdapter(@NonNull List<OnboardingItem> pages) {
            this.pages = pages;

        }

        @Override @NonNull
        public onBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new onBoardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container,parent,false));
        }

    @Override
        public void onBindViewHolder(@NonNull onBoardingViewHolder holder, int position) {
            holder.imageView.setImageResource(pages.get(position).getImage());
            holder.heading.setText(pages.get(position).getTitle());
            holder.description.setText(pages.get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return pages.size();
        }

        protected static class onBoardingViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView heading;
            TextView description;
            public onBoardingViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.imageView);
                heading    = view.findViewById(R.id.heading);
                description= view.findViewById(R.id.description);
            }
        }
}
