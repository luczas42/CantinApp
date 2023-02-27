package com.example.cantinappmobile.model;

import com.google.gson.annotations.SerializedName;

public class UserApiReturn {
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public UserApiReturn(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
