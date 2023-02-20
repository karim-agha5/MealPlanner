package com.example.mealplanner.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;


@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @ColumnInfo (name="id")
    @NonNull
    private String id;

    @ColumnInfo (name = "name")

    private String name;

    @Ignore
    private List<Meal> favoriteMeals;

    @Ignore
    private List<Meal> plannedMeals;

    public User() {
    }

    public User(@NonNull final String id, final String name, final List<Meal> favoriteMeals
    ,List<Meal> plannedMeals) {
        this.id = id;
        this.name = name;
        this.favoriteMeals = favoriteMeals;
        this.plannedMeals = plannedMeals;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    public void setId(@NonNull final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Meal> getFavoriteMeals() {
        return this.favoriteMeals;
    }

    public void setFavoriteMeals(final List<Meal> favoriteMeals) {
        this.favoriteMeals = favoriteMeals;
    }

    public List<Meal> getPlannedMeals() {
        return this.plannedMeals;
    }

    public void setPlannedMeals(final List<Meal> plannedMeals) {
        this.plannedMeals = plannedMeals;
    }
}
