package com.example.ecommerce.Models.Utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    Retrofit retrofit;

    public static RetrofitBuilder instance;


    public RetrofitBuilder() {
    }

    public static RetrofitBuilder getInstance(){
        if(instance == null){
            instance = new RetrofitBuilder();
        }
        return instance;
    }


    public Retrofit buildRetrofitObject() {
        if (retrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Urls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();
        }
        return retrofit;
    }

}
