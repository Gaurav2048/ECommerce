package com.example.ecommerce.Models.Interface.api;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchLinkInterface {
    @GET("suggestions")
    Call<Search> getSuggestions(@Query("phrase") String searchWords, @Query("limit") String llimit, @Query("offset") String offset);

    @GET("get_popular")
    Call<Search> getPopularProducts(@Query("phrase") String searchWords, @Query("limit") String llimit, @Query("offset") String offset);

}
