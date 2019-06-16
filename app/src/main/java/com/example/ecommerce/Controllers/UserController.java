package com.example.ecommerce.Controllers;

import android.content.Context;
import android.util.Log;

import com.example.ecommerce.Models.DataTypes.Login;
import com.example.ecommerce.Models.DataTypes.Register;
import com.example.ecommerce.Models.DataTypes.User;
import com.example.ecommerce.Models.Interface.Actions.UserActions;
import com.example.ecommerce.Models.Interface.api.userInterface;
import com.example.ecommerce.Models.Utilities.RetrofitBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserController {

    Context context;
    UserActions userActions;
    Retrofit retrofit;
    userInterface mUserInterface;

    public UserController(Context context, UserActions userActions) {
        this.context = context;
        this.userActions = userActions;
        retrofit = RetrofitBuilder.getInstance().buildRetrofitObject();
        mUserInterface = retrofit.create(userInterface.class);
    }


    public void Login(String userName, String password){
        userActions.onStartOperation();
        Map<String, String> hasmap = new HashMap<>();
        hasmap.put("email", userName);
        hasmap.put("password", password);
        Log.e( "Login: ", hasmap.toString());


        Call<User>   call = mUserInterface.login("application/json", new Login(userName, password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
//                Log.e( "onResponse: ", String.valueOf(response.body().getAccessToken()));
                    userActions.onUserLogin(response.body());
          }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userActions.onError(t);
            }
        });
    }


    public  void register(Register register){
        Call<Object> call = mUserInterface.register("application/json",register);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                userActions.registerSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                userActions.onError(t);
            }
        });
    }





}
