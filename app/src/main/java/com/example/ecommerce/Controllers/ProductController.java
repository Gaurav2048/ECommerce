package com.example.ecommerce.Controllers;

import android.util.Log;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.ProductList;
import com.example.ecommerce.Models.DataTypes.category;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.api.ProductsInterface;
import com.example.ecommerce.Models.Utilities.RetrofitBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductController {

ProductActions mProductActions;
Retrofit mRetrofit;
ProductsInterface mProductsInterface;
    private static final String TAG = "ProductController";

    public ProductController(ProductActions productActions) {
        this.mProductActions = productActions;
        mRetrofit = RetrofitBuilder.getInstance().buildRetrofitObject();
        mProductsInterface = mRetrofit.create(ProductsInterface.class);
    }


    public void get_Products_List_by_category(String category_id, String user_id, String offset){
        Call<ProductList> call = mProductsInterface.getProductsList( user_id,  offset);

        mProductActions.onFetchStart();
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                Log.e(TAG, "onResponse: "+response.body() );
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
    }


    public void get_New_popular_Products_List(String user_id){
        Call<ProductList> call = mProductsInterface.getMostPopular(user_id);

        mProductActions.onFetchStart();
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                Log.e(TAG, "onResponse: "+response.body() );
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
    }

    /*
    * with pagination
    * */

    public void get_all_new_products(String user_id, String count){
        mProductActions.onFetchStart();
        Call<ProductList> call = mProductsInterface.get_all_new_products(user_id, count);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
     }

    public void get_all_new_products_by_category(String user_id, String count, String category ){
        mProductActions.onFetchStart();
        Call<ProductList> call = mProductsInterface.get_all_new_products_by_category(user_id, count, category);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
    }

    public void get_new_popular_productsByCategory(String user_id,String count, String cateegory){
        mProductActions.onFetchStart();
        Call<ProductList> call = mProductsInterface.get_all_new_popular_products(user_id,count, cateegory);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
    }


    public void get_all_new_exclusive_products(String user_id,String count, String category){
        mProductActions.onFetchStart();
        Call<ProductList> call = mProductsInterface.get_all_new_exclusive_products(user_id,count, category);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
    }

    public void get_all_new_onsale_products(String user_id,String count, String category){
        mProductActions.onFetchStart();
        Call<ProductList> call = mProductsInterface.get_all_new_onsale_products(user_id,count, category);
        call.enqueue(new Callback<ProductList>() {
            @Override
            public void onResponse(Call<ProductList> call, Response<ProductList> response) {
                mProductActions.onFetchProgress(response.body().getProductList());
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                mProductActions.onFetchFailed(t);
            }
        });
    }




    public void getPopularList(){

          }





}
