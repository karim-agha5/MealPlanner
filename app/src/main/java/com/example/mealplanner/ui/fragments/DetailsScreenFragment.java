package com.example.mealplanner.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.google.android.material.textfield.TextInputLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class DetailsScreenFragment extends Fragment {


    private TextView mealName;

    private TextView area;

    private TextView instructions;

    private ImageView mealImage;

    private YouTubePlayerView video;

    private ArrayList<String> megure = new ArrayList<>();

    private String[] split;

    private Boolean youtubeURLisExists = false;


    private ProgressDialog progressDialog;

    private static final String TAG = "Exception";
    private Meal mealsItem;


    private Meal mealsItemSelectedFull;


    public DetailsScreenFragment() {
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
        return inflater.inflate(R.layout.fragment_details_screen, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsItem = DetailsScreenFragmentArgs.fromBundle(getArguments()).getMealDetails();

        mealName = view.findViewById(R.id.tv_title_details);
        area = view.findViewById(R.id.tv_area_details);
        instructions = view.findViewById(R.id.tv_instructions_details);
        mealImage = view.findViewById(R.id.img_meal_details);
        video = view.findViewById(R.id.video);

        getLifecycle().addObserver((LifecycleObserver) video);
        if (mealsItem.getYoutubeVideoUrl() != null && !mealsItem.getYoutubeVideoUrl().isEmpty() ) {

            split = mealsItem.getYoutubeVideoUrl().split("=");
            youtubeURLisExists = true;
            video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    if (youtubeURLisExists) {
                        String videoId = split[1];
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                }
            });
        } else {
            video.setVisibility(View.GONE);
        }

        mealName.setText(mealsItem.getName());
        if(mealsItem.getArea()!= null && !mealsItem.getArea().isEmpty()){
            area.setText(mealsItem.getArea());
        }else {
            area.setVisibility(View.GONE);
        }
        Glide.with(view.getContext()).load(mealsItem.getImageUrl()).into(mealImage);
        instructions.setText(mealsItem.getInstructions());
    }
}