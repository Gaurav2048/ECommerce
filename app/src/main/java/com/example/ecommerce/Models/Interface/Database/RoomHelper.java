package com.example.ecommerce.Models.Interface.Database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ecommerce.Models.DataTypes.Cart;
import com.example.ecommerce.Models.Utilities.Constants;

public class RoomHelper {

    private CartDatabase cartDatabase;

    Context context;
    public RoomHelper(Context context){
        this.context = context;
    }

    public CartDatabase getCrtDatabase() {
        if(cartDatabase == null){
            cartDatabase = Room.databaseBuilder(context,
                    CartDatabase.class, Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return cartDatabase;
    }


}
