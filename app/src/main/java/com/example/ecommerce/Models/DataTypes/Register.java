package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

public class Register {
    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("image")
    String image;


    public Register(String name, String email, String password, String image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public String getName() {
        return name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
