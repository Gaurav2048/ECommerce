package com.example.ecommerce.view.Utility;

import android.content.Context;
import android.util.TypedValue;

import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GeneralUtility {

    public static int DpToPx(int dp, Context context){
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                       context.getResources().getDisplayMetrics());
        return space;
    }

    public static String getStringFromObject(Product product){
        return  new Gson().toJson(product);
    }

    public static Product getProductFromString(String json){
        Type listType = new TypeToken<Product>(){}.getType();
        Product product = new Gson().fromJson(json, listType);
        return product;
    }

}
