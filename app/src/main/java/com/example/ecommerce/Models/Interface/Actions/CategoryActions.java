package com.example.ecommerce.Models.Interface.Actions;

import com.example.ecommerce.Models.DataTypes.category;

import java.util.List;

public interface CategoryActions {

    void onStartFetch();
    void onFetchProgress(List<category> categories);
    void onFetchComplete();
    void onError(Throwable t);

}
