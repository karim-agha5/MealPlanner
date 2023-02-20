package com.example.mealplanner.data.localdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mealplanner.model.User;

import java.util.List;


@Dao
public interface UserDAO {

        /*@Query("SELECT * FROM user")
        Flowable<User> getUser();*/
        @Query("SELECT * FROM user")
        LiveData<User> getUsers();

        @Query("SELECT * FROM user WHERE name = :email")
        LiveData<User> getUserByEmail(String email);
        @Insert
        void insertUser(User user);

        @Delete
        void deleteUser(User user);
 }

