package com.example.mealplanner.presenters.fragment;

import com.example.mealplanner.data.repositories.SearchMealsByQueryRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.MealsNetworkCallBack;
import com.example.mealplanner.presenters.contract.SearchMealsByQueryContract;
import com.example.mealplanner.ui.fragments.SearchFragment;
import com.example.mealplanner.ui.fragments.SearchByQueryFragment;

import java.util.ArrayList;

public class SearchByQueryPresenter implements SearchMealsByQueryContract {

    private SearchMealsByQueryRepository repository;
    private SearchByQueryFragment fragment;

    private String searchType;

    public SearchByQueryPresenter(SearchByQueryFragment mealsForSpecificAreaFragment) {
        fragment = mealsForSpecificAreaFragment;
        repository = new SearchMealsByQueryRepository();
    }


    @Override
    public void searchByQuery(String query) {

        switch (searchType) {
            case SearchFragment.categoryType: {

            }
            case SearchFragment.mealsType: {
                repository.searchMealsByQuery(query, new MealsNetworkCallBack() {
                    @Override
                    public void onSuccessResult(ArrayList<Meal> meals) {
                        fragment.showMealsResult(meals);
                }

                    @Override
                    public void onFailureResult(String errorMsg) {
                        fragment.showError(errorMsg);
                    }
                });
            }
            case SearchFragment.ingerdiantType: {
                //repository.searchIngredientsByQuery();
            }
        }


    }

    @Override
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
