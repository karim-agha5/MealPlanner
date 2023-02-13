package com.example.mealplanner.data.localdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "meal")
@TypeConverters(ArrayConverter.class)
public class Meal {


    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "area")
    private String area;

    @ColumnInfo(name = "instructions")
    private String instructions;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    @ColumnInfo(name = "youtube_video_url")
    private String youtubeVideoUrl;

    @ColumnInfo(name = "ingredients")
    private String[] ingredients;

    @ColumnInfo(name = "measures")
    private String[] measures;
}
