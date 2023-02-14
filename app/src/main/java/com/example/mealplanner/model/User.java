package com.example.mealplanner.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
    private List<String> meal;

    public User() {
    }

    public User(@NonNull final String id, final String name, final List<String> meal) {
        this.id = id;
        this.name = name;
        this.meal = meal;
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

    public List<String> getMeal() {
        return this.meal;
    }

    public void setMeal(final List<String> meal) {
        this.meal = meal;
    }
}
