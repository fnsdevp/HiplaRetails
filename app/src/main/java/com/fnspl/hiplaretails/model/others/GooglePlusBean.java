package com.fnspl.hiplaretails.model.others;

/**
 * Created by alokdas on 16/10/15.
 */
public class GooglePlusBean {

    String id, name, photoUrl, profileUrl, email, accessToken;

    public GooglePlusBean(String id, String name, String photoUrl, String profileUrl, String email, String accessToken) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.profileUrl = profileUrl;
        this.email = email;
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getEmail() {
        return email;
    }
}
