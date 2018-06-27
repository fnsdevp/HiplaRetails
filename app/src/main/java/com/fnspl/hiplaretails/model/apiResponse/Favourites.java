package com.fnspl.hiplaretails.model.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Favourites {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("productlist")
    @Expose
    private List<ListFavoutite> productlist = null;

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

    public List<ListFavoutite> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ListFavoutite> productlist) {
        this.productlist = productlist;
    }
}
