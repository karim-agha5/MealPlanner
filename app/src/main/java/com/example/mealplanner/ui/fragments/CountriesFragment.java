package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.data.AreaAdapter;
import com.example.mealplanner.model.Area;

import java.util.ArrayList;


public class CountriesFragment extends Fragment {

    private ArrayList<Area> countries;

    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;

    public CountriesFragment() {
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
        return inflater.inflate(R.layout.fragment_area_rec_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.recycleViewArea);
        countries = new ArrayList<>();

        countries.add(new Area(R.drawable.egypt,"Egyption"));
        countries.add(new Area(R.drawable.america,"American"));
        countries.add(new Area(R.drawable.british,"British"));
        countries.add(new Area(R.drawable.french,"French"));
        countries.add(new Area(R.drawable.polish,"Polish"));
        countries.add(new Area(R.drawable.canda,"Canadian"));
        countries.add(new Area(R.drawable.chine,"Chinese"));
        countries.add(new Area(R.drawable.croatian,"Croatian"));
        countries.add(new Area(R.drawable.dutch,"dutch"));
        countries.add(new Area(R.drawable.greece,"Greek"));
        countries.add(new Area(R.drawable.india,"Indian"));
        countries.add(new Area(R.drawable.irish,"Irish"));
        countries.add(new Area(R.drawable.italy,"Italian"));
        countries.add(new Area(R.drawable.japan,"Japanese"));
        countries.add(new Area(R.drawable.kenya,"Kenyan"));
        countries.add(new Area(R.drawable.malaysian,"Malaysian"));
        countries.add(new Area(R.drawable.mexico,"Mexican"));
        countries.add(new Area(R.drawable.moroco,"Moroccan"));
        countries.add(new Area(R.drawable.portug,"Portuguese"));
        countries.add(new Area(R.drawable.russian,"Russian"));
        countries.add(new Area(R.drawable.spani,"Spanish"));
        countries.add(new Area(R.drawable.thia,"Thai"));
        countries.add(new Area(R.drawable.turcia,"Turkish"));
        countries.add(new Area(R.drawable.tunisian,"Tunisian"));
        countries.add(new Area(R.drawable.vietname,"Vietnamese"));
        AreaAdapter adapter = new AreaAdapter(countries);
        layoutManager = new GridLayoutManager(getActivity(),2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
    }
}