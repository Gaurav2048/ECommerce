package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("id")
    String id;


    @SerializedName("itemName")
    String mItemName;

    @SerializedName("category")
    String mCategory;

    @SerializedName("description")
    String mDescription;

    @SerializedName("image1")
    String mImage1;

    @SerializedName("image2")
    String mImage2;

    @SerializedName("image3")
    String mImage3;


    @SerializedName("price")
    String mPrice;

    @SerializedName("discount")
    String mDiscount;

    @SerializedName("upvoted")
    String Upvote;

    @SerializedName("isupvoted")
    String isupvoted;

    @SerializedName("createdAt")
    String mCreatedAt;

    @SerializedName("updatedAt")
    String mUpdatedAt;


    public String getUpvote() {
        return Upvote;
    }

    public void setUpvote(String upvote) {
        Upvote = upvote;
    }

    public String getIsupvoted() {
        return isupvoted;
    }

    public void setIsupvoted(String isupvoted) {
        this.isupvoted = isupvoted;
    }


    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmItemName() {
        return mItemName;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmImage1() {
        return mImage1;
    }

    public String getmImage2() {
        return mImage2;
    }

    public String getmImage3() {
        return mImage3;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmDiscount() {
        return mDiscount;
    }



    public String getmCreatedAt() {
        return mCreatedAt;
    }

    public void setmCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    public void setmUpdatedAt(String mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }

    public void setmItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public void setmDiscount(String mDiscount) {
        this.mDiscount = mDiscount;
    }



    public void setmImage1(String mImage1) {
        this.mImage1 = mImage1;
    }

    public void setmImage2(String mImage2) {
        this.mImage2 = mImage2;
    }

    public void setmImage3(String mImage3) {
        this.mImage3 = mImage3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
