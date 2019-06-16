package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("success")
    boolean success;

    @SerializedName("id")
    int userId;

    @SerializedName("image")
    String image;

    @SerializedName("message")
    String message;

    @SerializedName("accessToken")
    String accessToken;

    public String getName() {
        return name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
