package com.example.mealplanner.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.mealplanner.R;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.HomePresenterContract;
import com.example.mealplanner.presenters.fragment.HomePresenter;
import com.example.mealplanner.ui.activities.MainActivity;
import com.example.mealplanner.ui.adapters.YouMayAlsoLikeAdapter;
import com.example.mealplanner.ui.fragments.state.HomeFragmentState;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class HomeFragment extends Fragment {

    private HomePresenterContract homePresenterContract;
    private HomeFragmentState homeFragmentState;
    private ProgressDialogHelper progressDialogHelper;
    private YouMayAlsoLikeAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView ivMealImage;
    private TextView tvMealName;
    private TextView tvMealArea;
    private Drawable[] images;
    private ContentLoadingProgressBar contentLoadingProgressBar;
    private final String TAG = "Exception";
    private String state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_you_may_also_like);
        ivMealImage = view.findViewById(R.id.iv_meal_image);
        tvMealName = view.findViewById(R.id.tv_meal_name);
        tvMealArea = view.findViewById(R.id.tv_meal_area);
        contentLoadingProgressBar = view.findViewById(R.id.daily_inspiration_image_loading_progress_bar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        homePresenterContract = new HomePresenter((MainActivity)getActivity());

        homeFragmentState = homePresenterContract.getHomeFragmentState();

        if(!homeFragmentState.hasSavedState()) {


            startProgressDialog();
            MutableLiveData<DataLayerResponse<ArrayList<Meal>>> response =
                    homePresenterContract.getRandomMeals();

            response.observe(this,dataLayerResponse -> {

                progressDialogHelper.stopProgressDialog();
                if(dataLayerResponse.getStatus() == Status.SUCCESS){
                    ArrayList<Meal> randomMeals;

                    homeFragmentState = homePresenterContract.getHomeFragmentState();
                    /*
                     * Elements could be null.
                     * If so, fill it with bitmaps through glide.
                     * If not, retrieve the saved bitmaps.
                     * */
                    images = homeFragmentState.getSavedImages();
                    if(!homeFragmentState.hasSavedState()){
                        randomMeals = getRandomMealsList(dataLayerResponse.getWrappedResponse());
                        homeFragmentState.setRandomMealsList(randomMeals);
                        homeFragmentState.setHasSavedState(true);

                    }


                    else{
                        randomMeals = homeFragmentState.getRandomMealsList();
                        images = homeFragmentState.getSavedImages();
                    }



                    handleDailyInspirationMeal(randomMeals.get(0));
                    randomMeals.remove(0);

                    //  adapter = new YouMayAlsoLikeAdapter(getActivity(), randomMeals);
                    adapter = new YouMayAlsoLikeAdapter(getActivity(),homePresenterContract,randomMeals,images);

                    LinearLayoutManager manager =
                            new LinearLayoutManager(
                                    getActivity(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                            );
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }

                else{
                    startAlertDialog(dataLayerResponse.getMessage());
                }
            });

        }


        else{

            ArrayList<Meal> randomMeals;

            homeFragmentState = homePresenterContract.getHomeFragmentState();
            /*
             * Elements could be null.
             * If so, fill it with bitmaps through glide.
             * If not, retrieve the saved bitmaps.
             * */
                images = homeFragmentState.getSavedImages();




                randomMeals = homeFragmentState.getRandomMealsList();
                images = homeFragmentState.getSavedImages();




            handleDailyInspirationMeal(randomMeals.get(0));
            randomMeals.remove(0);

            adapter = new YouMayAlsoLikeAdapter(getActivity(),homePresenterContract,randomMeals,images);

            LinearLayoutManager manager =
                    new LinearLayoutManager(
                            getActivity(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                    );
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);


        }

    }

    private void startProgressDialog(){
        progressDialogHelper =
                new ProgressDialogHelper(
                        getActivity(),
                        null,
                        "Hold on for a moment"
                );
        progressDialogHelper.startProgressDialog();
    }

    private void startAlertDialog(String errorMessage){
        AlertDialogHelper alertDialogHelper =
                new AlertDialogHelper(
                        getActivity(),
                        "Error",
                        errorMessage
                );

        alertDialogHelper.startAlertDialog();
    }

    private void handleDailyInspirationMeal(Meal dailyInspirationMeal){
        tvMealName.setText(String.valueOf(dailyInspirationMeal.getName()));
        tvMealArea.setText(String.valueOf(dailyInspirationMeal.getArea()));



        if(images[0] == null){

            Glide.with(getActivity())
                    .load(dailyInspirationMeal.getImageUrl())
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            contentLoadingProgressBar.setVisibility(View.GONE);
                            ivMealImage.setVisibility(View.VISIBLE);
                            ivMealImage.setImageDrawable(resource);
                            images[0] = resource;
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });


        }


        else{
            contentLoadingProgressBar.setVisibility(View.GONE);
            ivMealImage.setVisibility(View.VISIBLE);
            ivMealImage.setImageDrawable(images[0]);
        }


    }

    private ArrayList<Meal> getRandomMealsList(ArrayList<Meal> fullMealsList){
        ArrayList<Meal> randomMealsList = new ArrayList<>();

        ArrayList<Integer> uniqueRandomNumbers = generateRandomNumbers(40);

        for(int i = 0; i < uniqueRandomNumbers.size(); i++){
            randomMealsList.add(fullMealsList.get(uniqueRandomNumbers.get(i)));
        }

        return randomMealsList;
    }


    private ArrayList<Integer> generateRandomNumbers(int bound){
        HashSet<Integer> uniqueNumbersSet = new HashSet<>();
        Random random;
        int i = 0;
        while(i <= 10){
            random = new Random();
            Integer potentiallyUniqueNumber = random.nextInt(bound);
            if(!uniqueNumbersSet.contains(potentiallyUniqueNumber)){
                i++;
                uniqueNumbersSet.add(potentiallyUniqueNumber);
            }
        }

        return new ArrayList<>(uniqueNumbersSet);
    }


    public void insertMealIntoLocalDatabase(Meal meal){
        homePresenterContract.addMealToLocalDatabase(meal);
    }

}