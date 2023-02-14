package com.example.mealplanner.helper;

import android.app.AlertDialog;
import android.content.Context;

/**
 * This class makes it easier to create and dismiss an AlertDialog if you need multiple AlertDialogs
 * in your app
 *
 * All AlertDialogs are NOT cancelable nor have two buttons by default only for the purpose of this app
 * */

public class AlertDialogHelper {

    private Context context;
    private String title;
    private String message;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    public AlertDialogHelper(Context context,String title,String message){
        this.context = context;
        this.title = title;
        this.message = message;
    }

    private void setBuilder(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setNeutralButton("OK",(dialogInterface, i) ->{});
    }

    public void startAlertDialog(){
        setBuilder();
        alertDialog = builder.create();
        alertDialog.show();
    }

}
