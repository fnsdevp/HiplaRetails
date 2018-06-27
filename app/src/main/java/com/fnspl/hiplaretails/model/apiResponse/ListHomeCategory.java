package com.fnspl.hiplaretails.model.apiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListHomeCategory {

    @SerializedName("catid")
    @Expose
    private Integer catid;
    @SerializedName("parent")
    @Expose
    private Integer parent;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("has_child")
    @Expose
    private Integer hasChild;

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;}
}
