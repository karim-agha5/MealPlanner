package com.example.mealplanner.ui.adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealplanner.R;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.HomePresenterContract;
import com.example.mealplanner.ui.activities.MainActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;

public class YouMayAlsoLikeAdapter extends RecyclerView.Adapter<YouMayAlsoLikeAdapter.CustomViewHolder>{


    private Context context;
    private HomePresenterContract homePresenterContract;
    private ArrayList<Meal> listOfMeals;
    private Drawable[] images;
    private final String TAG = "Exception";

    public YouMayAlsoLikeAdapter(Context context,ArrayList<Meal> listOfMeals){
        this.context = context;
        this.listOfMeals = listOfMeals;
    }



    public YouMayAlsoLikeAdapter(Context context,HomePresenterContract homePresenterContract,
                                 ArrayList<Meal> listOfMeals,Drawable[] images){
        this.context = context;
        this.homePresenterContract = homePresenterContract;
        this.listOfMeals = listOfMeals;
        this.images = images;
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMealImagePreview;
        TextView tvYouMayAlsoLikeMealName;
        ImageButton ibMoreVert;
        ContentLoadingProgressBar imageLoadingProgressBar;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMealImagePreview = itemView.findViewById(R.id.iv_meal_image_preview);
            tvYouMayAlsoLikeMealName = itemView.findViewById(R.id.tv_you_may_also_like_meal_name);
            ibMoreVert = itemView.findViewById(R.id.ib_you_may_also_like_more_vert);
            imageLoadingProgressBar = itemView.findViewById(R.id.image_loading_progress_bar);
            handleMoreVertButton();
        }

        private MutableLiveData<DataLayerResponse> isMealAdded(Meal meal){
            DataLayerResponse dataLayerResponse = new DataLayerResponse();
            MutableLiveData<DataLayerResponse> isMealAddedResponse = new MutableLiveData<>();
            MutableLiveData<DataLayerResponse> response =
                    homePresenterContract.addMealToLocalDatabase(meal);
            response.observe((LifecycleOwner)context,dataLayerResponse1 -> {
                // If the meal already exists
                if(dataLayerResponse1.getStatus() == Status.SUCCESS){
                    dataLayerResponse.setStatus(Status.SUCCESS);
                    isMealAddedResponse.postValue(dataLayerResponse);
                }

                else{
                    dataLayerResponse.setStatus(Status.FAILURE);
                    isMealAddedResponse.postValue(dataLayerResponse);
                }
            });

            return isMealAddedResponse;
        }
        private void handleMoreVertButton(){

            ibMoreVert.setOnClickListener(view -> {

                /*
                 * Inflate the menu when the More Vert button is clicked
                 * and add a click behavior to each menu item
                 * */
                PopupMenu popupMenu = new PopupMenu(context,ibMoreVert);
                popupMenu.inflate(R.menu.you_may_also_like_more_vert_menu);
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    int position;
                    Meal meal;
                    MutableLiveData<DataLayerResponse> response;
                    switch (menuItem.getItemId()){
                        case R.id.action_add_to_favorite_meals:

                            //TODO remove and retrieve favorite meals from the local / remote database
                            //TODO retrieve meals from the local db if there's no internet connection
                            //TODO add in local list

                            position = getAbsoluteAdapterPosition();
                            meal = listOfMeals.get(position);
                            meal.setUserId(homePresenterContract.getCurrentUserId());
                            meal.setDate(new Date());

                            // If the meal is already in the local database, update the planned column's value
                            //TODO we probably don't need to check if the meal exists so maybe remove later.
                            MutableLiveData<DataLayerResponse> isFavoriteMealAddedResponse =
                                    isMealAdded(meal);


                            // Observe whether the meal was added in the local database or not
                            isFavoriteMealAddedResponse.observe((LifecycleOwner) context,dataLayerResponse -> {
                                // The meal is already in the local database
                                // Therefore, update the isPlanned column in the local database
                                // and the attribute in the meals local set
                                if(dataLayerResponse.getStatus() == Status.SUCCESS){

                                    MutableLiveData<DataLayerResponse> updateMealResponse =
                                            homePresenterContract.updateMealIsFavorite(
                                                    meal.getUserId(),
                                                    meal.getId(),
                                                    true
                                            );

                                    updateMealResponse.observe((LifecycleOwner) context,dataLayerResponse2 -> {

                                        if(dataLayerResponse2.getStatus() == Status.SUCCESS){
                                            Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                          //  meal.setFavorite(true);
                                        }
                                        else{
                                            Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    });


                                }


                                // The meal is not in the local database
                                // Therefore, add insert it.
                                else{

                                  //  meal.setFavorite(true);
                                    MutableLiveData<DataLayerResponse> updateMealResponse =
                                            homePresenterContract.addMealToLocalDatabase(meal);
                                    updateMealResponse.observe((LifecycleOwner) context, dataLayerResponse2 -> {
                                        if(dataLayerResponse2.getStatus() == Status.FAILURE){
                                            Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(context, "Added to Favorite Meals",
                                                    Toast.LENGTH_SHORT).show();
                                            meal.setFavorite(true);
                                        }
                                    });


                                }
                            });

                            return true;

                        case R.id.action_add_to_planned_meals:



                            //TODO add meals to remote db
                            //TODO add meals to local db if there's and there isn't internet connection
                            //TODO add meals to local list

                            position = getAbsoluteAdapterPosition();
                            meal = listOfMeals.get(position);
                            meal.setUserId(homePresenterContract.getCurrentUserId());


                            MaterialDatePicker.Builder datePickerBuilder = MaterialDatePicker.Builder.datePicker();

                            MaterialDatePicker datePicker = datePickerBuilder.build();
                            datePicker.show(((MainActivity)context).getSupportFragmentManager(),"Date Picker");

                            datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                                @Override
                                public void onPositiveButtonClick(Long selectedDate) {
                                    // Get the offset from our timezone and UTC.
                                    TimeZone timeZoneUTC = TimeZone.getDefault();
                                    // It will be negative, so that's the -1
                                    int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                                    // Create a date format, then a date object with our offset
                                    SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                                    meal.setDate(new Date(selectedDate + offsetFromUTC));





                                    // If the meal is already in the local database, update the planned column's value
                                    //TODO we probably don't need to check if the meal exists so maybe remove later.
                                    MutableLiveData<DataLayerResponse> isPlannedMealAddedResponse =
                                            isMealAdded(meal);

                                    // Observe whether the meal was added in the local database or not
                                    isPlannedMealAddedResponse.observe((LifecycleOwner) context,dataLayerResponse -> {
                                        // The meal is already in the local database
                                        // Therefore, update the isPlanned column in the local database
                                        // and the attribute in the meals local set
                                        if(dataLayerResponse.getStatus() == Status.SUCCESS){

                                            homePresenterContract.updateMealDate(
                                                    meal.getUserId(),
                                                    meal.getId(),
                                                    meal.getDate()
                                            );

                                            MutableLiveData<DataLayerResponse> updateMealResponse =

                                                    homePresenterContract.updateMealIsPlanned(
                                                            meal.getUserId(),
                                                            meal.getId(),
                                                            true
                                                    );

                                            updateMealResponse.observe((LifecycleOwner) context,dataLayerResponse2 -> {

                                                if(dataLayerResponse2.getStatus() == Status.SUCCESS){
                                                    Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                            Toast.LENGTH_SHORT).show();
                                                    //   meal.setPlanned(true);
                                                }
                                                else{
                                                    Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                            Toast.LENGTH_SHORT).show();
                                                }

                                            });


                                        }


                                        // The meal is not in the local database
                                        // Therefore, add insert it.
                                        else{

                                            //   meal.setPlanned(true);
                                            MutableLiveData<DataLayerResponse> updateMealResponse =
                                                    homePresenterContract.addMealToLocalDatabase(meal);
                                            updateMealResponse.observe((LifecycleOwner) context, dataLayerResponse2 -> {
                                                if(dataLayerResponse2.getStatus() == Status.FAILURE){
                                                    Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(context, "Added to Planned Meals",
                                                            Toast.LENGTH_SHORT).show();
                                                    //         meal.setPlanned(true);
                                                }
                                            });


                                        }
                                    });







                                }
                            });



/*

                            // If the meal is already in the local database, update the planned column's value
                            //TODO we probably don't need to check if the meal exists so maybe remove later.
                            MutableLiveData<DataLayerResponse> isPlannedMealAddedResponse =
                                    isMealAdded(meal);

                            // Observe whether the meal was added in the local database or not
                            isPlannedMealAddedResponse.observe((LifecycleOwner) context,dataLayerResponse -> {
                                // The meal is already in the local database
                                // Therefore, update the isPlanned column in the local database
                                // and the attribute in the meals local set
                                if(dataLayerResponse.getStatus() == Status.SUCCESS){

                                    homePresenterContract.updateMealDate(
                                            meal.getUserId(),
                                            meal.getId(),
                                            meal.getDate()
                                    );

                                    MutableLiveData<DataLayerResponse> updateMealResponse =

                                            homePresenterContract.updateMealIsPlanned(
                                            meal.getUserId(),
                                            meal.getId(),
                                            true
                                    );

                                    updateMealResponse.observe((LifecycleOwner) context,dataLayerResponse2 -> {

                                        if(dataLayerResponse2.getStatus() == Status.SUCCESS){
                                            Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                         //   meal.setPlanned(true);
                                        }
                                        else{
                                            Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                    });


                                }


                                // The meal is not in the local database
                                // Therefore, add insert it.
                                else{

                                 //   meal.setPlanned(true);
                                    MutableLiveData<DataLayerResponse> updateMealResponse =
                                            homePresenterContract.addMealToLocalDatabase(meal);
                                    updateMealResponse.observe((LifecycleOwner) context, dataLayerResponse2 -> {
                                        if(dataLayerResponse2.getStatus() == Status.FAILURE){
                                            Toast.makeText(context, dataLayerResponse2.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(context, "Added to Planned Meals",
                                                    Toast.LENGTH_SHORT).show();
                                   //         meal.setPlanned(true);
                                        }
                                    });


                                }
                            });
*/


                            return true;


                        default: return false;
                    }
                });

                popupMenu.show();

            });

        }
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CustomViewHolder(layoutInflater.inflate(R.layout.you_may_also_like_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvYouMayAlsoLikeMealName.setText(String.valueOf(listOfMeals.get(position).getName()));
        handleImageView(holder,position);
    }

    @Override
    public int getItemCount() {
        return listOfMeals.size();
    }

    private void handleImageView(CustomViewHolder holder,int position){


        if(images[position + 1] == null){

            Glide.with(context)
                    .load(listOfMeals.get(position).getImageUrl())
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            holder.imageLoadingProgressBar.setVisibility(View.GONE);
                            holder.ivMealImagePreview.setVisibility(View.VISIBLE);
                            holder.ivMealImagePreview.setImageDrawable(resource);
                            images[position + 1] = resource;
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });

        }


        else{
            holder.imageLoadingProgressBar.setVisibility(View.GONE);
            holder.ivMealImagePreview.setVisibility(View.VISIBLE);
            holder.ivMealImagePreview.setImageDrawable(images[position + 1]);
        }

    }
}
