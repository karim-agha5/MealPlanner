package com.example.mealplanner.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.presenters.contract.SearchMealsByQueryContract;
import com.example.mealplanner.presenters.fragment.SearchByQueryPresenter;
import com.example.mealplanner.ui.adapters.MealsAreaAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Locale;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

public class SearchByQueryFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchMealsByQueryContract presenter;
    private String searchType;
    private TextInputEditText searchView;

    public SearchByQueryFragment() {
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
        return inflater.inflate(R.layout.fragment_search_meals_by_query, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_allMeals);
        presenter = new SearchByQueryPresenter(this);
        searchType = SearchByQueryFragmentArgs.fromBundle(getArguments()).getSearchType();
        presenter.setSearchType(searchType);
        searchView = view.findViewById(R.id.search_by_query);
      /* searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.searchByQuery(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<Object> emitter) throws Throwable {

                        searchView.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                presenter.searchByQuery(s.toString());
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });

                    }
                })

                .subscribe(s -> Log.i("TAG", "onCreate: " + s));

    }


    public void showMealsResult(ArrayList<Meal> meals) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        MealsAreaAdapter adapter = new MealsAreaAdapter(meals);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void showError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }
}