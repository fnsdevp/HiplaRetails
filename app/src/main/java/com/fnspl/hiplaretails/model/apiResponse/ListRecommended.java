package com.fnspl.hiplaretails.model.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListRecommended {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bar_code")
    @Expose
    private String barCode;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_image_folder")
    @Expose
    private String productImageFolder;
    @SerializedName("zone_id")
    @Expose
    private String zoneId;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("images")
    @Expose
    private List<String> images = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductImageFolder() {
        return productImageFolder;
    }

    public void setProductImageFolder(String productImageFolder) {
        this.productImageFolder = productImageFolder;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
