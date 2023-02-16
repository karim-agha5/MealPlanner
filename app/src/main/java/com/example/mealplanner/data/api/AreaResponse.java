package com.example.mealplanner.data.api;

import com.example.mealplanner.model.Area;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaResponse {

    @SerializedName("meals")
    private ArrayList<Area> areasList;

    public AreaResponse(){}

    public AreaResponse(final ArrayList<Area> areasList) {
        this.areasList = areasList;
    }

    public ArrayList<Area> getAreasList() {
        return this.areasList;
    }

    public void setAreasList(final ArrayList<Area> areasList) {
        this.areasList = areasList;
    }
}