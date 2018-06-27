package com.fnspl.hiplaretails.model.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecommendedDetails {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("productlist")
    @Expose
    private List<ListRecommended> productlist = null;

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

    public List<ListRecommended> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ListRecommended> productlist) {
        this.productlist = productlist;
    }
}
