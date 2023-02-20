package com.example.mealplanner.presenters.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealplanner.data.DataLayerResponse;
import com.example.mealplanner.data.repositories.LoginRepository;
import com.example.mealplanner.model.User;
import com.example.mealplanner.presenters.contract.LoginPresenterContract;
import com.example.mealplanner.ui.contract.DatabaseDelegate;

import io.reactivex.Flowable;

public class LoginPresenter implements LoginPresenterContract {

   // private LoginRemoteService loginRemoteService;
    private LoginRepository loginRepository;
    private DatabaseDelegate databaseDelegate;

    public LoginPresenter(DatabaseDelegate databaseDelegate){
    //    loginRemoteService = new LoginRemoteServiceImpl(databaseDelegate.getDatabaseAccess());
        this.databaseDelegate = databaseDelegate;
        loginRepository = new LoginRepository(databaseDelegate.getDatabaseAccess());
    }

    public MutableLiveData<DataLayerResponse<LiveData<User>>> login(String email, String password){
        return loginRepository.login(email, password);
    }
}
