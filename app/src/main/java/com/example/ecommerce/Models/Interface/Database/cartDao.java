package com.example.ecommerce.Models.Interface.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ecommerce.Models.DataTypes.Cart;

import java.util.List;

@Dao
public interface cartDao {


    @Insert
    void insertItemToCart (Cart cart);

    @Query("SELECT * FROM Cart")
    List<Cart> get_Cart_List ();

    @Query("SELECT COUNT(*) FROM Cart")
    int get_Cart_Size();

    @Update
    void updateCartItem(Cart cart);

    @Delete
    void delete_Cart_item (Cart cart);



}
