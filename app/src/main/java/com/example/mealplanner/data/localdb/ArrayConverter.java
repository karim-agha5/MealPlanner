package com.example.mealplanner.data.localdb;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class ArrayConverter {


    @TypeConverter
    String fromArrayToString(String[] arr){
        return new Gson().toJson(arr);
    }

    @TypeConverter
    String[] fromStringToArray(String str){
        return new Gson().fromJson(str,new TypeToken<String[]>(){}.getType());
    }

}
