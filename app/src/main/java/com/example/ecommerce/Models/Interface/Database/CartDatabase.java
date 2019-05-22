package com.example.ecommerce.Models.Interface.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.example.ecommerce.Models.DataTypes.Cart;

@Database(entities = {Cart.class},version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    public abstract cartDao cartDao() ;
}
