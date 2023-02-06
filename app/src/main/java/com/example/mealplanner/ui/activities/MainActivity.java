package com.example.mealplanner.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        initUI();
        setupBottomNavigationView();


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
                    Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.search_fragment:
                    //TODO go to search fragment
                    Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.planned_meals_fragment:
                    //TODO go to planned meals fragment
                    Toast.makeText(this, "PLANNED MEALS", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.notifications_fragment:
                    //TODO go to notifications fragment
                    Toast.makeText(this, "NOTIFICATIONS", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.profile_fragment:
                    //TODO go to profile fragment
                    Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show();
                    return true;


                default: return false;
            }
        });
    }
}