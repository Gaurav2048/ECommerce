package com.example.ecommerce.Models.Utilities;

import com.example.ecommerce.Models.DataTypes.category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Utility {

    public static String getCategoryString(List<category> categoryList){
        return new Gson().toJson(categoryList);
    }

    public static category getCategory_Object_From_String(String json, int position){
        Type listType = new TypeToken<List<category>>(){}.getType();
        List<category> categories = new Gson().fromJson(json, listType);
        return categories.get(position);
    }

    public static List<category> getCategory_list(String json){
        Type listType = new TypeToken<List<category>>(){}.getType();
        List<category> categories = new Gson().fromJson(json, listType);
        return categories;
    }

    public static int getCategory_List_size_String(String json){
        Type listType = new TypeToken<List<category>>(){}.getType();
        List<category> categories = new Gson().fromJson(json, listType);
        return categories.size();
    }

}
