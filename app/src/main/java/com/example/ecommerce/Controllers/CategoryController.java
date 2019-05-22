package com.example.ecommerce.Controllers;

import android.content.Context;

import com.example.ecommerce.Models.DataTypes.CategoryList;
import com.example.ecommerce.Models.DataTypes.category;
import com.example.ecommerce.Models.Interface.Actions.CategoryActions;
import com.example.ecommerce.Models.Interface.api.CategoryInterface;
import com.example.ecommerce.Models.Sharedpreferences.AppPreferences;
import com.example.ecommerce.Models.Utilities.RetrofitBuilder;
import com.example.ecommerce.Models.Utilities.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryController {
    Retrofit mRetrofit;
    CategoryInterface categoryInterface;
    CategoryActions categoryActions;
    Context context;
    public CategoryController(Context context,CategoryActions categoryActions){
        mRetrofit = new RetrofitBuilder().buildRetrofitObject();
        this.context = context;
        this.categoryActions = categoryActions;
        categoryInterface = mRetrofit.create(CategoryInterface.class);
    }

    public void getCategories(){
        categoryActions.onStartFetch();
        Call<CategoryList> call = categoryInterface.getCategories();

        call.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                new AppPreferences(context).setCategoryPreference(Utility.getCategoryString(response.body().getCategoryList()));
                categoryActions.onFetchProgress(response.body().getCategoryList());
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable t) {
            categoryActions.onError(t);
            }
        });
    }

}
