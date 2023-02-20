package com.example.mealplanner.presenters.fragment;

import com.example.mealplanner.data.api.responses.MealsNetworkCallBack;
import com.example.mealplanner.data.repositories.SearchMealsByQueryRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.model.RootCategoriesList;

import com.example.mealplanner.presenters.contract.SearchMealsByQueryContract;
import com.example.mealplanner.ui.fragments.AllCategoryFragment;
import com.example.mealplanner.ui.fragments.SearchFragment;
import com.example.mealplanner.ui.fragments.SearchByQueryFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchByQueryPresenter implements SearchMealsByQueryContract {

    private SearchMealsByQueryRepository repository;
    private SearchByQueryFragment fragment;
    private AllCategoryFragment categoryFragment;

    private String searchType;

    public SearchByQueryPresenter(SearchByQueryFragment mealsForSpecificAreaFragment) {
        fragment = mealsForSpecificAreaFragment;
        repository = new SearchMealsByQueryRepository();
    }

    public SearchByQueryPresenter(final AllCategoryFragment categoryFragment) {
        this.categoryFragment = categoryFragment;
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
