package com.example.mealplanner.data.localdb;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;

public class ArrayConverter {


    @TypeConverter
    public String fromArrayToString(String[] arr){
        return new Gson().toJson(arr);
    }

    @TypeConverter
    public String[] fromStringToArray(String str){
        return new Gson().fromJson(str,new TypeToken<String[]>(){}.getType());
    }

    @TypeConverter
    public String fromDateToString(Date date){return new Gson().toJson(date);}

    @TypeConverter
    public Date fromStringToDate(String str){
        return new Gson().fromJson(str,new TypeToken<Date>(){}.getType());
    }

}
