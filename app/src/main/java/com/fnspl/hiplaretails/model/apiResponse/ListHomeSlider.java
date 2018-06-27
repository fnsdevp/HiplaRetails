package com.fnspl.hiplaretails.model.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListHomeSlider {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("offerslist")
    @Expose
    private List<HomeSliderOffer> offerslist = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomeSliderOffer> getOfferslist() {
        return offerslist;
    }

    public void setOfferslist(List<HomeSliderOffer> offerslist) {
        this.offerslist = offerslist;
    }
}
