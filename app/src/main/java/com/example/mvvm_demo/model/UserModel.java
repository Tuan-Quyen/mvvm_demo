package com.example.mvvm_demo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("username")
    private String username;
    @SerializedName("phone")
    private String phone;
    @SerializedName("gender")
    private String gender;

    public String getUsername() {
        return username != null ? username : "";
    }

    public String getPhone() {
        return phone != null ? phone : "";
    }

    public String getGender() {
        return gender != null ? gender : "";
    }
}
