package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.NetworkCallBack;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;
import com.example.mealplanner.presenters.fragment.MealsForSpecificAreaPresenter;
import com.example.mealplanner.ui.adapters.MealsForSpecificAreaAdapter;

import java.util.ArrayList;
import java.util.List;

import io.grpc.Context;

public class MealsForSpecificAreaFragment extends Fragment implements NetworkCallBack , MealsForSpecificAreaContract {

    private ImageView mealImage;
    private TextView mealTxt;

     private TextView areaTxt;
    private MealsForSpecificAreaAdapter mealsForSpecificAreaAdapter;
    private MealsForSpecificAreaPresenter mealsForSpecificAreaPresenter;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private List<Meal> mealsItemList = new ArrayList<>();
    private ProgressBar progressBar;
    private static final String TAG = "MealForSpecificArea";
    private Context context;

    public MealsForSpecificAreaFragment() {
        // Required empty public constructor
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
        areaTxt = view.findViewById(R.id.areaTxt);
        String country = null;
        mealsForSpecificAreaPresenter = new MealsForSpecificAreaPresenter(this);
        mealsForSpecificAreaPresenter.getAllMeal(country);


    }


    @Override
    public void onSuccessResult(List<Meal> meals) {
        mealsItemList = meals;
        LinearLayoutManager linearLayout = new LinearLayoutManager(requireContext());
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        mealsForSpecificAreaAdapter = new MealsForSpecificAreaAdapter(mealsItemList);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(mealsForSpecificAreaAdapter);

    }

    @Override
    public void onFailureResult(String errorMsg) {
        Log.i(TAG, "onFailureResult: "+errorMsg.toString());

    }

    @Override
    public void getAllMeal(String country) {

    }

    @Override
    public void addToFavorite(Meal meals) {

    }
}