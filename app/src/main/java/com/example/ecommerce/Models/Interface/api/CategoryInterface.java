package com.example.ecommerce.Models.Interface.api;

import com.example.ecommerce.Models.DataTypes.CategoryList;
import com.example.ecommerce.Models.DataTypes.category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryInterface {

    @GET("categories")
    Call<CategoryList> getCategories();

}
