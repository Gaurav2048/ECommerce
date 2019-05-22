package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

public class category {

    @SerializedName("id")
    String mCategoryid;

    @SerializedName("categoryName")
    String mCategoryName;

    @SerializedName("image")
    String mImage;

    public String getmCategoryid() {
        return mCategoryid;
    }

    public void setmCategoryid(String mCategoryid) {
        this.mCategoryid = mCategoryid;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
