package com.example.ecommerce.Models.Interface.api;


import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.ProductList;
import com.example.ecommerce.Models.DataTypes.category;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ProductsInterface {

    /**
     * main page data devided as per category sorted by upvotes
     * */

    @GET("products")
    Call<ProductList> getProductsList(@Query("category") String category_id, @Query("user_id") String user_id);


    /***
     * products by sorted upvotes
     *
     */

    @GET("get_popular_products")
    Call<ProductList> getMostPopular(@Query("user_id") String user_id);

    /*
    * Get all new Products ie order by created at desc
    * */

    @GET("new_products")
    Call<ProductList> get_all_new_products(@Query("user_id") String user_id, @Query("id") String count);

    @GET("new_popular_products")
    Call<ProductList> get_all_new_popular_products(@Query("user_id") String user_id,@Query("id") String count);

    @GET("new_exclusive_products")
    Call<ProductList> get_all_new_exclusive_products(@Query("user_id") String user_id,@Query("id") String count);

    @GET("new_onsale_products")
    Call<ProductList> get_all_new_onsale_products(@Query("user_id") String user_id,@Query("id") String count);



    @GET("/")
    Call<List<category>> getCategories();


    @POST("/filtered_products")
    Call<List<Product>> getFilteredPRoduct(
            @QueryMap Map<String, String > map
            );


    @GET("/product/{id}")
    Call<Product> get_Product_Detail(@Path("id") String id);





}
