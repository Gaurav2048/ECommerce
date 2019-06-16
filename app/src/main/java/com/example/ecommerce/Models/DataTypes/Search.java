package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("phrase")
    String  phrase;

    @SerializedName("products")
    List<Product> productList;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
