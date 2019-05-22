package com.example.ecommerce.Models.Interface.Actions;

import com.example.ecommerce.Models.DataTypes.Cart;

import java.util.List;

public interface CartAction {

    void onResultCount(int Count);
    void onResultCartList(List<Cart> cartList);
    void onRemoveCartItem();
    void onCartItemAdded();

}
