package com.fnspl.hiplaretails.model.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeSliderOffer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("offer_type")
    @Expose
    private String offerType;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("offer_description")
    @Expose
    private String offerDescription;
    @SerializedName("imagepath")
    @Expose
    private String imagepath;
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("date_to")
    @Expose
    private String dateTo;
    @SerializedName("discount_percentage")
    @Expose
    private Integer discountPercentage;
    @SerializedName("offer_product_id")
    @Expose
    private String offerProductId;
    @SerializedName("offer_category_id")
    @Expose
    private String offerCategoryId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("category_name")
    @Expose
    private Object categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getOfferProductId() {
        return offerProductId;
    }

    public void setOfferProductId(String offerProductId) {
        this.offerProductId = offerProductId;
    }

    public String getOfferCategoryId() {
        return offerCategoryId;
    }

    public void setOfferCategoryId(String offerCategoryId) {
        this.offerCategoryId = offerCategoryId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Object getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Object categoryName) {
        this.categoryName = categoryName;
    }

}
