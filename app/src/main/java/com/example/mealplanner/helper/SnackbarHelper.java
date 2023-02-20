package com.example.mealplanner.helper;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarHelper {

    private Context context;
    private View view;
    private String message;
    private String actionText;
    private int length;

    public SnackbarHelper(){}

    public SnackbarHelper(final Context context, final View view, final String message, final String actionText, final int length) {
        this.context = context;
        this.view = view;
        this.message = message;
        this.actionText = actionText;
        this.length = length;
    }

    public void setContext(final Context context) {
        this.context = context;
    }

    public void setView(final View view) {
        this.view = view;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setActionText(final String actionText) {
        this.actionText = actionText;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public void showSnackbar(View.OnClickListener listener){
        Snackbar.make(
                        context,
                        view,
                        message,
                        length)
                .setAction(actionText,listener).show();
    }
}
