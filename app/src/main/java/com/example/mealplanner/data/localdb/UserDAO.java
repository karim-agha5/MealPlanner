package com.example.mealplanner.data.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealplanner.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface UserDAO {

        @Query("SELECT * FROM user")
        Flowable<User> getUser();


        @Insert
        void insertUser(User user);

        @Delete
        void deleteUser(User user);
 }

