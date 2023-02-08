package com.example.mealplanner.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mealplanner.ImageAdapter;
import com.example.mealplanner.OnboardingItem;
import com.example.mealplanner.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ImageAdapter adapter;
    private LinearLayout layoutOnBoardingIndicators;
    private MaterialButton materialButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        setupOnboarding();

        ViewPager2 view = findViewById(R.id.onboardingViewPager);
        layoutOnBoardingIndicators=findViewById(R.id.layoutOnboardingIndicators);
        materialButton=findViewById(R.id.btn_next);

        view.setAdapter(adapter);

        setupOnboardingCordinates();
        setSpaceInCordinates(0);
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setSpaceInCordinates(position);
            }
        });

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view.getCurrentItem()<adapter.getItemCount()){
                    view.setCurrentItem(view.getCurrentItem()+1);
                }else{
                 //   startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        });
    }
    private void setupOnboarding(){
        List<OnboardingItem> item= new ArrayList<>();

        OnboardingItem onboarditem=new OnboardingItem();
        onboarditem.setTitle("food plan");
        onboarditem.setDescription("fodd planner");
        onboarditem.setImage(R.drawable.food);

        OnboardingItem onboarditemTwo =new OnboardingItem();
        onboarditemTwo.setTitle("food plan");
        onboarditemTwo.setDescription("fodd planner");
        onboarditemTwo.setImage(R.drawable.food);

        OnboardingItem onboarditemThree =new OnboardingItem();
        onboarditemThree.setTitle("food plan");
        onboarditemThree.setDescription("fodd planner");
        onboarditemThree.setImage(R.drawable.food);

        item.add(onboarditem);
        item.add(onboarditemTwo);
        item.add(onboarditemThree);

        adapter = new ImageAdapter(item);
    }

    private void setupOnboardingCordinates(){
        ImageView[] indicators= new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0; i<indicators.length;i++) {
            indicators[i]=new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),R.drawable.onboarding
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }
    }

    private void setSpaceInCordinates(int index){
        int childCount = layoutOnBoardingIndicators.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imageView = (ImageView) layoutOnBoardingIndicators.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboardingrect));
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding));
            }
        }
        if(index == adapter.getItemCount() -1 ){
            materialButton.setText("Start");
        }
    }
}