package com.example.ecommerce.Models.Utilities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.User;
import com.example.ecommerce.Models.DataTypes.category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Utility {

    public static String getCategoryString(List<category> categoryList){
        return new Gson().toJson(categoryList);
    }
    public static String getCategoryString(category category){
        return new Gson().toJson(category);
    }

    public static String convertLoginDetailToString(User user){
        return new Gson().toJson(user);
    }


    public static category getCategory_Object_From_String(String json){
        Type listType = new TypeToken<List<category>>(){}.getType();
        category categories = new Gson().fromJson(json, listType);
        return categories;
    }

    public static category getCategoryObject(String json){
        Type listType = new TypeToken<category>(){}.getType();
        category categories = new Gson().fromJson(json, listType);
        return categories;
    }

    public static  String getStringFromObject(Product product){
        return  new Gson().toJson(product);
    }

    public static int getCategory_List_size_String(String json){
        Type listType = new TypeToken<List<category>>(){}.getType();
        List<category> categories = new Gson().fromJson(json, listType);
        return categories.size();
    }

    public static String convertWishListToString(List<Product> products){
        return  new Gson().toJson(products);
    }

    public static List<Product> getWishList(String json){
        Type listType = new TypeToken<List<Product>>(){}.getType();
        List<Product> categories = new Gson().fromJson(json, listType);
        return categories;
    }

    public static Product getProductObject(String json){
        Type listType = new TypeToken<Product>(){}.getType();
        Product product = new Gson().fromJson(json, listType);
        return product;
    }

    public static List<String> getHistory(String json){
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> history = new Gson().fromJson(json, listType);
        return history;
    }


    public static String getHistoryJson(List<String> history){
        return  new Gson().toJson(history);
    }

    public static void buttonAnimation(View view){


        PropertyValuesHolder animation = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.15f);
        PropertyValuesHolder animation1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.15f);
        PropertyValuesHolder animation3 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animation3 = PropertyValuesHolder.ofFloat(View.TRANSLATION_Z, 1f, 1.15f);
        }

        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, animation, animation1, animation3);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(250);
        animator.start();
    }

}
