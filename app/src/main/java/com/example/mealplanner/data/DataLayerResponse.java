package com.example.mealplanner.data;

/*
* Responsible for communicating within the Data Layer and with other layers
* */

import com.example.mealplanner.helper.Status;

public class DataLayerResponse<T> {

    private Status status;
    private String message;
    private T wrappedResponse;

    public DataLayerResponse(){}


    public DataLayerResponse(final Status status, final T wrappedResponse) {
        this(status,null,wrappedResponse);
    }

    public DataLayerResponse(final Status status,final String message, final T wrappedResponse) {
        this.status = status;
        this.message = message;
        this.wrappedResponse = wrappedResponse;
    }


    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public T getWrappedResponse() {
        return this.wrappedResponse;
    }

    public void setWrappedResponse(final T wrappedResponse) {
        this.wrappedResponse = wrappedResponse;
    }
}
