package com.example.ecommerce.Models.DataTypes;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("success")
    Boolean success;

    @SerializedName("message")
    String message;

    public RegisterResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
