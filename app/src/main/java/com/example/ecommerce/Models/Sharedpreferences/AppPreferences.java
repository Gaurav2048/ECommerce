package com.example.ecommerce.Models.Sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    public static final String CATEGORY_PREF = "category_pref";
    public static final String CATEGORY = "category";

    public Context context;

    public AppPreferences(Context context) {
        this.context = context;
    }

    public void setCategoryPreference(String category_json){
        SharedPreferences sharedpreferences = context.getSharedPreferences(CATEGORY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(CATEGORY, category_json);
        editor.apply();
        editor.commit();
    }

    public String getCategoryFromPreference(){
        SharedPreferences sharedpreferences = context.getSharedPreferences(CATEGORY_PREF, Context.MODE_PRIVATE);
        return  sharedpreferences.getString(CATEGORY, null);
    }

    public boolean clearCategoryPref(){
        SharedPreferences sharedpreferences = context.getSharedPreferences(CATEGORY_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        return  editor.clear().commit();
    }

}
