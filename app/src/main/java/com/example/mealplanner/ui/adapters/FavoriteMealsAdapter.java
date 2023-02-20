package com.example.mealplanner.ui.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealplanner.R;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.FavoriteMealsPresenterContract;
import com.example.mealplanner.ui.activities.MainActivity;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.CustomViewHolder>{

    private Context context;
    private List<Meal> favoriteMealsList;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView recyclerView;
    private FavoriteMealsPresenterContract favoriteMealsPresenterContract;
    private Drawable[] images;
    private boolean isUndoPressed;
    private final String TAG = "Exception";


    public FavoriteMealsAdapter(Context context, List<Meal> favoriteMealsList,
                                FavoriteMealsPresenterContract favoriteMealsPresenterContract){
        this.context = context;
        this.favoriteMealsList = favoriteMealsList;
        this.favoriteMealsPresenterContract = favoriteMealsPresenterContract;
    }


    public FavoriteMealsAdapter(Context context, List<Meal> favoriteMealsList,Drawable[] images){
        this.context = context;
        this.favoriteMealsList = favoriteMealsList;
        this.images = images;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        itemTouchHelper = new ItemTouchHelper(getCardViewSwipeCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout favoriteMealItemContainer;
        ImageView ivFavoriteMealImagePreview;
        TextView tvFavoriteMealName;
        TextView tvFavoriteMealAreaName;
        ImageButton ibFavoriteMealMoreVert;
        ContentLoadingProgressBar imageLoadingProgressBar;
        private boolean isUndoPressed;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteMealItemContainer = itemView.findViewById(R.id.favorite_meal_item_container);
            ivFavoriteMealImagePreview = itemView.findViewById(R.id.iv_favorite_meal_preview);
            tvFavoriteMealName = itemView.findViewById(R.id.tv_favorite_meal_name);
            tvFavoriteMealAreaName = itemView.findViewById(R.id.tv_favorite_meal_area);
            ibFavoriteMealMoreVert = itemView.findViewById(R.id.ib_favorite_meal_more_vert);
            imageLoadingProgressBar = itemView.findViewById(R.id.image_loading_progress_bar);
        }



        public void handleSnackbar(View itemView,Meal removedMeal,int position){

            Snackbar snackbar =  Snackbar.make(
                    context,
                    itemView,
                    "Deleting the meal....",
                    Snackbar.LENGTH_LONG
            ).setAction("Undo",view -> {

                    }).addCallback(new Snackbar.Callback(){
                @Override
                public void onShown(Snackbar sb) {
                    super.onShown(sb);
                }
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    if(event == DISMISS_EVENT_TIMEOUT){
                       handleMealDeletion(removedMeal,position);
                    }
                    if(event == BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_ACTION){
                        favoriteMealsList.add(position,removedMeal);
                        notifyItemInserted(position);
                    }
                }
            });
            snackbar.show();
        }



        public void handleMealDeletion(Meal meal,int position){

            if(!meal.isPlanned()){
                Log.i(TAG, "deleting the meal ");
                /*favoriteMealsList.remove(position);
                notifyItemRemoved(position);*/
                favoriteMealsPresenterContract.deleteMeal(meal);
            }
            else{
                Log.i(TAG, "updating the meal ");
                Log.i(TAG, "attempt to remove ");
                favoriteMealsPresenterContract.updateMealIsFavorite(
                  meal.getUserId(), meal.getId(), false
                );
            }


            }


    }



    @NonNull
    @Override
    public FavoriteMealsAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CustomViewHolder(layoutInflater.inflate(R.layout.favorite_meal_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMealsAdapter.CustomViewHolder holder, int position) {
        holder.tvFavoriteMealName.setText(String.valueOf(favoriteMealsList.get(position).getName()));
        holder.tvFavoriteMealAreaName.setText(String.valueOf(favoriteMealsList.get(position).getArea()));


        Glide.with(context)
                .load(favoriteMealsList.get(position).getImageUrl())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.imageLoadingProgressBar.setVisibility(View.GONE);
                        holder.ivFavoriteMealImagePreview.setVisibility(View.VISIBLE);
                        holder.ivFavoriteMealImagePreview.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });








        holder.ibFavoriteMealMoreVert.setOnClickListener(view -> {

            /*
             * Inflate the menu when the More Vert button is clicked
             * and add a click behavior to each menu item
             * */
            PopupMenu popupMenu = new PopupMenu(context,holder.ibFavoriteMealMoreVert);
            popupMenu.inflate(R.menu.favorite_meal_item_more_vert_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.action_remove_from_favorite_meals:

                        //TODO remove and retrieve favorite meals from the local / remote database
                        //TODO retrieve meals from the local db if there's no internet connection
                        //TODO add in local list

                        Meal removedMeal = favoriteMealsList.get(position);
                        favoriteMealsList.remove(position);
                        notifyItemRemoved(position);
                      //  holder.handleMealDeletion(removedMeal,position);

                        holder.handleSnackbar(holder.itemView,removedMeal,position);



                        return true;

                    case R.id.action_add_to_planned_meals:

                        /*//TODO remove and retrieve planned meals from the local / remote database
                        //TODO retrieve meals from the local db if there's no internet connection
                        favoriteMealsList.get(position).setDate(new Date());
                        favoriteMealsPresenterContract.updateMealDate(
                                favoriteMealsList.get(position).getUserId(),
                                favoriteMealsList.get(position).getId(),
                                new Date()
                        );
                        favoriteMealsPresenterContract.updateMealIsPlanned(
                                favoriteMealsList.get(position).getUserId(),
                                favoriteMealsList.get(position).getId(),
                                true
                        );*/


                        Meal meal = favoriteMealsList.get(position);
                        meal.setUserId(favoriteMealsList.get(position).getUserId());

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






                                        favoriteMealsPresenterContract.updateMealDate(
                                                meal.getUserId(),
                                                meal.getId(),
                                                meal.getDate()
                                        );

                                        MutableLiveData<DataLayerResponse> updateMealResponse =

                                                favoriteMealsPresenterContract.updateMealIsPlanned(
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
                        });









                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

                        return true;


                    default: return false;
                }
            });

            popupMenu.show();
        });





    }

    @Override
    public int getItemCount() {
        return favoriteMealsList.size();
    }


    private ItemTouchHelper.SimpleCallback getCardViewSwipeCallback(){
        ItemTouchHelper.SimpleCallback simpleCallback =
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                        int position = viewHolder.getAdapterPosition();

                        switch (direction){
                            case ItemTouchHelper.LEFT:
                                Meal favoriteMeal = favoriteMealsList.get(position);
                                favoriteMealsList.remove(position);
                                notifyItemRemoved(position);
                             //  handleMealDeletion(favoriteMeal,position);

                                handleSnackbar(viewHolder.itemView,favoriteMeal,position);

                                break;

                        }
                    }

                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                                .addSwipeLeftBackgroundColor(ContextCompat.getColor(context,R.color.red))
                                .addSwipeLeftActionIcon(R.drawable.baseline_delete_24)
                                .create()
                                .decorate();

                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                };

        return simpleCallback;
    }



    public void handleSnackbar(View itemView,Meal removedMeal,int position){

        Snackbar snackbar =  Snackbar.make(
                context,
                itemView,
                "Deleting the meal....",
                Snackbar.LENGTH_LONG
        ).setAction("Undo",view -> {

        }).addCallback(new Snackbar.Callback(){
            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
            }
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if(event == DISMISS_EVENT_TIMEOUT){
                    handleMealDeletion(removedMeal,position);
                }
                if(event == BaseTransientBottomBar.BaseCallback.DISMISS_EVENT_ACTION){
                    favoriteMealsList.add(position,removedMeal);
                    notifyItemInserted(position);
                }
            }
        });
        snackbar.show();
    }

    public void handleMealDeletion(Meal meal,int position){

        if(!meal.isPlanned()){
            Log.i(TAG, "deleting the meal ");
                /*favoriteMealsList.remove(position);
                notifyItemRemoved(position);*/
            favoriteMealsPresenterContract.deleteMeal(meal);
        }
        else{
            Log.i(TAG, "updating the meal ");
            Log.i(TAG, "attempt to remove ");
            favoriteMealsPresenterContract.updateMealIsFavorite(
                    meal.getUserId(), meal.getId(), false
            );
        }


    }
}
