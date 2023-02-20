package com.example.mealplanner.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.mealplanner.R;
import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;
import com.example.mealplanner.model.User;
import com.example.mealplanner.ui.contract.DatabaseDelegate;

public class WelcomeActivity extends AppCompatActivity implements DatabaseDelegate {

    private NavController navController;
    private DatabaseAccess databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setUpActionBarWithNavController();
        databaseAccess = new DatabaseAccess(this);
    }

    /**
     * @Return NavController
     * */
    private NavController getNavController(){
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment_container);
        navController = navHostFragment.getNavController();

        return navController;
    }

    /**
     * Sets up an Actionbar with an already created Navigation Controller.
     * */
    private void setUpActionBarWithNavController(){
        NavigationUI.setupActionBarWithNavController(this,getNavController());
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public DatabaseAccess getDatabaseAccess() {
        return databaseAccess;
    }
}