package com.example.mealplanner.presenters.contract;

import com.example.mealplanner.model.Meal;


import java.util.List;

public interface InterfaceMealFromSpecificCategory {
    public void responseOfDataOnSuccess(List<Meal> mealsList);
}
