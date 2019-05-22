package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryList {

     @SerializedName("products")
    List<category> categoryList;

    public List<category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<category> categoryList) {
        this.categoryList = categoryList;
    }
}
