package com.example.mealplanner.model;

public class Area {

    private int image;
    private String countryName;

    public Area(final int image, final String countryName) {
        this.image = image;
        this.countryName = countryName;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(final int image) {
        this.image = image;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }
}
