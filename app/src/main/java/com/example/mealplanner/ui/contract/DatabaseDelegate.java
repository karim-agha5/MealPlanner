package com.example.mealplanner.ui.contract;

import com.example.mealplanner.data.datasource.dbaccess.DatabaseAccess;

public interface DatabaseDelegate {

    DatabaseAccess getDatabaseAccess();
}
