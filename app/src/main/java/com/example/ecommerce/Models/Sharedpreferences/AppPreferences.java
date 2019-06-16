package com.example.ecommerce.Models.Sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.User;
import com.example.ecommerce.Models.Utilities.Utility;

import java.util.List;

public class AppPreferences {

    public static final String CATEGORY_PREF = "category_pref";
    public static final String CATEGORY = "category";
    private static final String LOGIN_PREF = "login_pref";
    private static final String LOGIN = "login";
    private static final String WISH_PREF = "wish_pref";
    private static final String WISHES = "wishes";
    private static final String SEARCH_HISTOR_PREF = "search_history_pref";
    private static final String SEARCH_HISTORY = "search_history";


    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public Context context;

    public AppPreferences(Context context) {
        this.context = context;
    }

    public void setCategoryPreference(String category_json){
         sharedpreferences = context.getSharedPreferences(CATEGORY_PREF, Context.MODE_PRIVATE);
         editor = sharedpreferences.edit();
         editor.putString(CATEGORY, category_json);
         editor.apply();
         editor.commit();
    }

    public String getCategoryFromPreference(){
         sharedpreferences = context.getSharedPreferences(CATEGORY_PREF, Context.MODE_PRIVATE);
        return  sharedpreferences.getString(CATEGORY, null);
    }

    public boolean clearCategoryPref(){
         sharedpreferences = context.getSharedPreferences(CATEGORY_PREF, Context.MODE_PRIVATE);
         editor = sharedpreferences.edit();
         return  editor.clear().commit();
    }

    public String getLoginDetails(){
         sharedpreferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
         return  sharedpreferences.getString(LOGIN, null);
    }

    public boolean setLoginDetails(User user){

        sharedpreferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString(LOGIN, Utility.convertLoginDetailToString(user));
        editor.apply();
        return   editor.commit();
    }

    public String getWishList(){
        sharedpreferences = context.getSharedPreferences(WISH_PREF, Context.MODE_PRIVATE);
        return  sharedpreferences.getString(WISHES, null);
    }

    public boolean setWishList(List<Product> products){

        sharedpreferences = context.getSharedPreferences(WISH_PREF, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString(WISHES, Utility.convertWishListToString(products));
        editor.apply();
        return   editor.commit();
    }


    public boolean clearLoginDetails(){
        sharedpreferences = context.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        return  editor.clear().commit();
    }

    public boolean setSearchHistory(String searches){
        sharedpreferences = context.getSharedPreferences(SEARCH_HISTOR_PREF, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString(SEARCH_HISTORY, searches);
        editor.apply();
        return   editor.commit();
    }

    public String getSearchHistroy(){
        sharedpreferences = context.getSharedPreferences(SEARCH_HISTOR_PREF, Context.MODE_PRIVATE);
        return  sharedpreferences.getString(SEARCH_HISTORY, null);
    }

    public boolean clearSEarchData(){
        sharedpreferences = context.getSharedPreferences(SEARCH_HISTOR_PREF, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        return  editor.clear().commit();
    }


}
