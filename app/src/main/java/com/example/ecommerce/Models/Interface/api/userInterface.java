package com.example.ecommerce.Models.Interface.api;


import android.util.Log;

import com.example.ecommerce.Models.DataTypes.Login;
import com.example.ecommerce.Models.DataTypes.Register;
import com.example.ecommerce.Models.DataTypes.User;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface userInterface {


    @POST("auth/signin")
    Call<User> login(@Header("Content-type")String header, @Body Login login);


    @POST("auth/signup")
    Call<Object> register(@Header("Content-type")String header, @Body Register register);


}
