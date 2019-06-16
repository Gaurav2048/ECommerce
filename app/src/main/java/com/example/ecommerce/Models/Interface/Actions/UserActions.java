package com.example.ecommerce.Models.Interface.Actions;

import com.example.ecommerce.Models.DataTypes.User;

import org.json.JSONObject;

public interface UserActions {

    void onUserLogin( User user);

    void onError(Throwable t);

    void onStartOperation();

    void registerSuccess(Object user);


}
