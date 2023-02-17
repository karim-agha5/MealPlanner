package com.example.mealplanner.ui.fragments;

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
import com.example.mealplanner.ui.adapters.YouMayAlsoLikeAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class HomeFragment extends Fragment {

    private HomePresenterContract homePresenterContract;
    private ProgressDialogHelper progressDialogHelper;
    private YouMayAlsoLikeAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView ivMealImage;
    private TextView tvMealName;
    private TextView tvMealArea;
    private ContentLoadingProgressBar contentLoadingProgressBar;
    private final String TAG = "Exception";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenterContract = new HomePresenter();
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
        startProgressDialog();
        MutableLiveData<DataLayerResponse<ArrayList<Meal>>> response =
                homePresenterContract.getRandomMeals();

        response.observe(this,dataLayerResponse -> {

            progressDialogHelper.stopProgressDialog();
            if(dataLayerResponse.getStatus() == Status.SUCCESS){
                ArrayList<Meal> randomMeals = getRandomMealsList(dataLayerResponse.getWrappedResponse());
                handleDailyInspirationMeal(randomMeals.get(0));
                randomMeals.remove(0);
                adapter = new YouMayAlsoLikeAdapter(getActivity(),randomMeals);
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


        Glide.with(getActivity())
                .load(dailyInspirationMeal.getImageUrl())
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        contentLoadingProgressBar.setVisibility(View.GONE);
                        ivMealImage.setVisibility(View.VISIBLE);
                        ivMealImage.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    private ArrayList<Meal> getRandomMealsList(ArrayList<Meal> fullMealsList){
        ArrayList<Meal> randomMealsList = new ArrayList<>();
        /*List<Integer> uniqueRandomNumbers =
                random.ints(11,1,11)
                .boxed()
                .collect(Collectors.toList());*/

        ArrayList<Integer> uniqueRandomNumbers = new ArrayList<>();
        generateRandomNumbers(uniqueRandomNumbers);

       /* new Thread(
                () -> {
                    while(!areNumbersUnique(uniqueRandomNumbers)){
                        generateRandomNumbers(uniqueRandomNumbers);
                    }
                    adapter.notifyDataSetChanged();
                }
        ).start();*/

        for(int i = 0; i < uniqueRandomNumbers.size(); i++){
            randomMealsList.add(fullMealsList.get(uniqueRandomNumbers.get(i)));
        }

        return randomMealsList;
    }


    private void generateRandomNumbers(ArrayList<Integer> uniqueNumbersList){
        Random random;
        for(int i = 0; i < 11; i++){
            random = new Random();
            uniqueNumbersList.add(random.nextInt(40));
        }
    }

    private boolean areNumbersUnique(List<Integer> randomNumbers){
        boolean isUnique = true;
        for(int i = 0; i < randomNumbers.size() - 1 && isUnique; i++){
            for(int j = i + 1; j < randomNumbers.size() && isUnique; j++){
                if(randomNumbers.get(i) == randomNumbers.get(j)){
                    isUnique = false;
                }
            }
        }

        return isUnique;
    }
}