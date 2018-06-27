package com.fnspl.hiplaretails.model.apiResponse;



public class ObjectRes<T> extends GenRes {
    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    private T response;
}
