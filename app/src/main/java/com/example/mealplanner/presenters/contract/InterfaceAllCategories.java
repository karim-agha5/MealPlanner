package com.example.mealplanner.presenters.contract;


import com.example.mealplanner.model.EachCategoryModel;

import java.util.List;

public interface InterfaceAllCategories {
    public void responseOfDataOnSuccess(List<EachCategoryModel> categoriesReceived);
}
