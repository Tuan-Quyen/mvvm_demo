package com.example.mvvm_demo.fragment.user;

import com.example.mvvm_demo.model.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    private List<UserModel> dataUser;
    private String error;

    UserResponse(String error) {
        this.error = error;
    }

    List<UserModel> getDataUser() {
        return dataUser;
    }

    String getError() {
        return error;
    }
}
