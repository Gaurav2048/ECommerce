package com.example.ecommerce.Models.Interface.Actions;

import com.example.ecommerce.Models.DataTypes.Product;
import com.example.ecommerce.Models.DataTypes.category;

import java.util.List;

public interface ProductActions {

    void onFetchStart();
    void onFetchProgress(Product product);
    void onFetchProgress(List<Product> products);
    void onFetchComplete();
    void onFetchFailed(Throwable t);


}
