package com.example.mealplanner.helper;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * This class makes it easier to create and dismiss an ProgressDialog
 * if you need multiple ProgressDialogs in your app
 * */


public class ProgressDialogHelper {

    private Context context;
    private String title;
    private String message;
    private ProgressDialog progressDialog;

    public ProgressDialogHelper(Context context,String title,String message){
        this.context = context;
        this.message = message;
        this.title = title;
    }

    public void startProgressDialog(){
        progressDialog = progressDialog.show(context,title,message);
    }
    public void stopProgressDialog(){
        progressDialog.cancel();
    }
}
