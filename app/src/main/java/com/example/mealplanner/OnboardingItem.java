package com.example.mealplanner;

public class OnboardingItem {
    private int image;
    private String title;
    private String description;


    public OnboardingItem(final int image, final String title, final String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public OnboardingItem() {
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(final int image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
