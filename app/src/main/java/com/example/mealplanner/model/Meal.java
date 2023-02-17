package com.example.mealplanner.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mealplanner.data.localdb.ArrayConverter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "meal", primaryKeys = {"user_id","meal_id"})
@TypeConverters(ArrayConverter.class)
public class Meal {

    @NonNull
    @ColumnInfo(name = "user_id")
    private String userId;

    @NonNull
    @ColumnInfo(name = "meal_id")
    @SerializedName("idMeal")
    private String id;

    @ColumnInfo(name = "name")
    @SerializedName("strMeal")
    private String name;

    @ColumnInfo(name = "category")
    @SerializedName("strCategory")
    private String category;

    @ColumnInfo(name = "area")
    @SerializedName("strArea")
    private String area;

    @ColumnInfo(name = "instructions")
    @SerializedName("strInstructions")
    private String instructions;

    @ColumnInfo(name = "image_url")
    @SerializedName("strMealThumb")
    private String imageUrl;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "youtube_video_url")
    @SerializedName("strYoutube")
    private String youtubeVideoUrl;

    @ColumnInfo(name = "ingredients")
    private String[] ingredients;

    @ColumnInfo(name = "measures")
    private String[] measures;

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;
    @ColumnInfo(name = "date")
    private Date date;

    public Meal(){}

    public Meal(@NonNull final String userId, @NonNull final String id, final String name,
                final String category, final String area, final String instructions,
                final String imageUrl, final String imagePath, final String youtubeVideoUrl,
                final String[] ingredients, final String[] measures) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
        this.youtubeVideoUrl = youtubeVideoUrl;
        this.ingredients = ingredients;
        this.measures = measures;
    }


    @NonNull
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(@NonNull final String userId) {
        this.userId = userId;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(final String area) {
        this.area = area;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(final String imagePath) {
        this.imagePath = imagePath;
    }

    public String getYoutubeVideoUrl() {
        return this.youtubeVideoUrl;
    }

    public void setYoutubeVideoUrl(final String youtubeVideoUrl) {
        this.youtubeVideoUrl = youtubeVideoUrl;
    }

    public String[] getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(final String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getMeasures() {
        return this.measures;
    }

    public void setMeasures(final String[] measures) {
        this.measures = measures;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

    public void setFavorite(final boolean favorite) {
        this.isFavorite = favorite;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }
}
