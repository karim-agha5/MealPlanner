package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.ui.adapters.AreaAdapter;
import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.helper.AlertDialogHelper;
import com.example.mealplanner.helper.ProgressDialogHelper;
import com.example.mealplanner.helper.Status;
import com.example.mealplanner.model.Area;
import com.example.mealplanner.presenters.fragment.AreasPresenter;

import java.util.ArrayList;


public class CountriesFragment extends Fragment {

    private ArrayList<Area> countries;

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressDialogHelper progressDialogHelper;

    private AreasPresenter areasPresenter;

    public CountriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        areasPresenter = new AreasPresenter();
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
        handleAreasResponse();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startProgressDialog();
    }

    private void handleAreasResponse(){
        MutableLiveData<DataLayerResponse<ArrayList<Area>>> response = areasPresenter.getListOfAreas();
        response.observe(this,dataLayerResponse -> {

            /*
             * Once the countries list is downloaded.
             * Display the list
             * */
            if(dataLayerResponse.getStatus() == Status.SUCCESS) {
                progressDialogHelper.stopProgressDialog();

                countries = dataLayerResponse.getWrappedResponse();
                countries = filterData(countries);
                fillCountriesWithDrawables(countries);
                AreaAdapter adapter = new AreaAdapter(countries);
                layoutManager = new GridLayoutManager(getActivity(),2);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(adapter);
                rv.setHasFixedSize(true);
            }
            else{
                progressDialogHelper.stopProgressDialog();
                startAlertDialog();
            }
        });
    }


    private void startProgressDialog(){
        progressDialogHelper = new ProgressDialogHelper(
                getActivity(),
                null,
                "Hold on for a moment"
        );
        progressDialogHelper.startProgressDialog();
    }

    private void startAlertDialog(){
        AlertDialogHelper alertDialogHelper =
                new AlertDialogHelper(
                        getActivity(),
                        "Unknown Error",
                        "Cannot get countries"
                );

        alertDialogHelper.startAlertDialog();
    }

    private void fillCountriesWithDrawables(ArrayList<Area> countries){
        countries.get(0).setImage(R.drawable.america);
        countries.get(1).setImage(R.drawable.british);
        countries.get(2).setImage(R.drawable.canda);
        countries.get(3).setImage(R.drawable.chine);
        countries.get(4).setImage(R.drawable.croatian);
        countries.get(5).setImage(R.drawable.dutch);
        countries.get(6).setImage(R.drawable.egypt);
        countries.get(7).setImage(R.drawable.french);
        countries.get(8).setImage(R.drawable.greece);
        countries.get(9).setImage(R.drawable.india);
        countries.get(10).setImage(R.drawable.irish);
        countries.get(11).setImage(R.drawable.italy);
        countries.get(12).setImage(R.drawable.jamaica);
        countries.get(13).setImage(R.drawable.japan);
        countries.get(14).setImage(R.drawable.kenya);
        countries.get(15).setImage(R.drawable.malaysian);
        countries.get(16).setImage(R.drawable.mexico);
        countries.get(17).setImage(R.drawable.moroco);
        countries.get(18).setImage(R.drawable.polish);
        countries.get(19).setImage(R.drawable.portug);
        countries.get(20).setImage(R.drawable.russian);
        countries.get(21).setImage(R.drawable.spani);
        countries.get(22).setImage(R.drawable.thia);
        countries.get(23).setImage(R.drawable.tunisian);
        countries.get(24).setImage(R.drawable.turcia);
        countries.get(25).setImage(R.drawable.vietname);
    }

    private ArrayList<Area> filterData(ArrayList<Area> countries){
        ArrayList<Area> list = new ArrayList<>();
        for(int i = 0; i < countries.size(); i++){
            if(!countries.get(i).getName().equals("Unknown")){
                list.add(countries.get(i));
            }
        }
        return list;
    }

}