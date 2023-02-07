package com.example.mealplanner.ui.activities;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.mealplanner.R;
import com.example.mealplanner.ui.fragments.HomeFragment;
import com.example.mealplanner.ui.fragments.NotificationsFragment;
import com.example.mealplanner.ui.fragments.PlannedMealsFragment;
import com.example.mealplanner.ui.fragments.ProfileFragment;
import com.example.mealplanner.ui.fragments.SearchFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private NotificationsFragment notificationsFragment;
    private ProfileFragment profileFragment;
    private PlannedMealsFragment plannedMealsFragment;
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
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        searchFragment = new SearchFragment();
        notificationsFragment = new NotificationsFragment();
        plannedMealsFragment = new PlannedMealsFragment();
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.notifications_fragment);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(1);

    }

    /**
     * Sets the bottom navigation view icons to navigate between different fragments
     * */
    private void setupBottomNavigationView(){

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_fragment:
                    // TODO go to home fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
                    return true;

                case R.id.search_fragment:
                    //TODO go to search fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                    Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.planned_meals_fragment:
                    //TODO go to planned meals fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,plannedMealsFragment).commit();
                    Toast.makeText(this, "PLANNED MEALS", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.notifications_fragment:
                    //TODO go to notifications fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,notificationsFragment).commit();
                    Toast.makeText(this, "NOTIFICATIONS", Toast.LENGTH_SHORT).show();
                    return true;


                case R.id.profile_fragment:
                    //TODO go to profile fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                    Toast.makeText(this, "PROFILE", Toast.LENGTH_SHORT).show();
                    return true;


                default: return false;
            }
        });
    }
}