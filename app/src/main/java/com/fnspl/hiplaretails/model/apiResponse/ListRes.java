package com.fnspl.hiplaretails.model.apiResponse;

import java.util.List;

/**
 * Created by root on 6/3/18.
 */

public class ListRes<T> extends GenRes {

    List<T> response;

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }
}
