package com.example.mealplanner.data;

/*
* Responsible for communicating within the Data Layer and with other layers
* */

import com.example.mealplanner.Status;

public class DataLayerResponse<T> {

    private Status status;
    private T wrappedResponse;

    public DataLayerResponse(){}

    public DataLayerResponse(final Status status, final T wrappedResponse) {
        this.status = status;
        this.wrappedResponse = wrappedResponse;
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
