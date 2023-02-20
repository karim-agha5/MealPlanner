package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealplanner.R;
import com.example.mealplanner.model.EachCategoryModel;
import com.example.mealplanner.model.RootCategoriesList;
import com.example.mealplanner.presenters.contract.InterfaceAllCategories;
import com.example.mealplanner.presenters.fragment.SearchByQueryPresenter;
import com.example.mealplanner.ui.adapters.AllCategoriesAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.stream.Collectors;


public class AllCategoryFragment extends Fragment {

    private List<EachCategoryModel> categories;
    public static TextInputEditText textInputEditText;
    private AllCategoriesAdapter allCategoriesAdapter;
    private RecyclerView recyclerView;

    private SearchByQueryPresenter search;



    public AllCategoryFragment() {
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
        return inflater.inflate(R.layout.fragment_all_category, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInputEditText = view.findViewById(R.id.tinput_search_Categoreies);

        recyclerView = view.findViewById(R.id.rv_Categories);

       search = new SearchByQueryPresenter(this);


        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                allCategoriesAdapter = new AllCategoriesAdapter(categories.stream().filter(
                        AreaModel -> AreaModel.getStrCategory().toLowerCase().startsWith(s.toString().toLowerCase())).collect(Collectors.toList()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(allCategoriesAdapter);
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    public void showCategory(List<EachCategoryModel> categoriesLists) {
        categories = categoriesLists;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        allCategoriesAdapter = new AllCategoriesAdapter(categories);
        recyclerView.setAdapter(allCategoriesAdapter);

    }
}