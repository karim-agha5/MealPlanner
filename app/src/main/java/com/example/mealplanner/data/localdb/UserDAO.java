package com.example.mealplanner.data.localdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealplanner.model.Users;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface UserDAO {
        @Query("SELECT * FROM users")
        Flowable<List<Users>> getAllUser();


        @Insert
        void insertUser(Users user);

        @Delete
        void deleteUser(Users user);
 }

