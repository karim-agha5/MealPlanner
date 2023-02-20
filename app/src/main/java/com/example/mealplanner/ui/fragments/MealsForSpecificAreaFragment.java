package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mealplanner.R;
import com.example.mealplanner.data.repositories.MealsForAreaRepository;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;
import com.example.mealplanner.presenters.fragment.MealsForSpecificAreaPresenter;
import com.example.mealplanner.ui.adapters.MealsAreaAdapter;

import java.util.ArrayList;

public class MealsForSpecificAreaFragment extends Fragment  {

    private ImageView mealImage;
    private TextView mealTxt;

     private TextView areaTxt;
    private ProgressDialogHelper progressDialogHelper;
    private MealsAreaAdapter mealsAreaAdapter;
    private MealsForSpecificAreaPresenter mealsForSpecificAreaPresenter;
    private MealsForSpecificAreaContract mealsForSpecificAreaContract;
    private MealsForAreaRepository mealsForAreaRepository;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private ProgressBar progressBar;
    private static final String TAG = "MealForSpecificArea";
    private String country;

    public MealsForSpecificAreaFragment() {
        // Required empty public constructor
        mealsForSpecificAreaContract = new MealsForSpecificAreaPresenter(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_for_specific_area, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealImage = view.findViewById(R.id.meal_image);
        mealTxt = view.findViewById(R.id.area_meal);
        recyclerView = view.findViewById(R.id.recycleView_meals);
        progressBar = view.findViewById(R.id.progressBar);

        country = MealsForSpecificAreaFragmentArgs.fromBundle(getArguments()).getArea();

        startProgressDialog();
        mealsForSpecificAreaContract.getAllMeals(country);
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


    public void showData(ArrayList<Meal> meals) {
        progressDialogHelper.stopProgressDialog();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealsAreaAdapter = new MealsAreaAdapter(meals);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mealsAreaAdapter);

    }


}