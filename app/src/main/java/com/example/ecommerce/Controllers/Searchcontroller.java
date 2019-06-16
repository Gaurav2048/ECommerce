package com.example.ecommerce.Controllers;

import android.content.Context;

import com.example.ecommerce.Models.DataTypes.Search;
import com.example.ecommerce.Models.Interface.Actions.ProductActions;
import com.example.ecommerce.Models.Interface.UI_Helpers.SearchInterface;
import com.example.ecommerce.Models.Interface.api.SearchLinkInterface;
import com.example.ecommerce.Models.Utilities.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Searchcontroller {

    public Retrofit retrofit;
    SearchInterface searchInterface;
    SearchLinkInterface mSearchLinkInterface;
    Context context;

    public Searchcontroller(SearchInterface searchInterface, Context context) {
        this.searchInterface = searchInterface;
        this.context = context;
        retrofit = RetrofitBuilder.getInstance().buildRetrofitObject();
        mSearchLinkInterface = retrofit.create(SearchLinkInterface.class);
    }

    public void getSuggestionsFromPhrase(String phrase, String limit, String offset){
        Call<Search> call = mSearchLinkInterface.getSuggestions(phrase,limit, offset );

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                searchInterface.onPhraseSelected(response.body());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                searchInterface.onError(t);
            }
        });

    }


    public void getPopularResults(String phrase, String limit, String offset){
        Call<Search> call = mSearchLinkInterface.getPopularProducts(phrase,limit, offset );

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                searchInterface.onPhraseSelected(response.body());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                searchInterface.onError(t);
            }
        });

    }

}
