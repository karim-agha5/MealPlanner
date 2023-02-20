package com.example.mealplanner.ui.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.mealplanner.R;
import com.example.mealplanner.data.api.responses.RandomMealsResponse;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.data.datasource.meals.impl.RandomMealsRemoteServiceImpl;
import com.example.mealplanner.ui.contract.DatabaseDelegate;
import com.example.mealplanner.ui.fragments.HomeFragment;
import com.example.mealplanner.ui.fragments.CountriesFragment;
import com.example.mealplanner.ui.fragments.PlannedMealsFragment;
import com.example.mealplanner.ui.fragments.ProfileFragment;
import com.example.mealplanner.ui.fragments.SearchFragment;
import com.example.mealplanner.ui.fragments.state.HomeFragmentState;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Observable;


public class MainActivity extends AppCompatActivity implements DatabaseDelegate {

    private BottomNavigationView bottomNavigationView;
    private  NavController navController;
    private HomeFragmentState homeFragmentState = new HomeFragmentState();
    private DatabaseAccess databaseAccess;
    private final String TAG = "Exception";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initUI();
        setupBottomNavigationView();
        databaseAccess = new DatabaseAccess(this);

        navController= Navigation.findNavController(this,R.id.container);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    // Initializes all the UI components
    private void initUI(){
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }

    /**
     * Sets the bottom navigation view icons to navigate between different fragments
     * */
    private void setupBottomNavigationView(){

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_fragment:
                    // TODO go to home fragment
                    navController.navigate(R.id.home_fragment);
                    Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.search_fragment:
                    //TODO go to search fragment
                    navController.navigate(R.id.search_fragment);
                    Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.planned_meals_fragment:
                    //TODO go to planned meals fragment
                    navController.navigate(R.id.planned_meals_fragment);
                    Toast.makeText(this, "PLANNED MEALS", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.countries_fragment:
                    //TODO go to notifications fragment
                    navController.navigate(R.id.countries_fragment);
                    Toast.makeText(this, "COUNTRIES", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.profile_fragment:
                    //TODO go to profile fragment
                    navController.navigate(R.id.profile_fragment);
                    Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show();
                    return true;


                default: return false;
            }
        });
    }

    public HomeFragmentState getHomeFragmentState(){
        return homeFragmentState;
    }

    @Override
    public DatabaseAccess getDatabaseAccess() {
        //TODO potential memory leak, as it was initialized in the Welcome Activity
        // and this class isn't singleton. Check later.
        return databaseAccess;
    }
}