package com.example.mvvm_demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("socket_id")
    @Expose
    private String socketId;

    public String getName() {
        return name;
    }

    public String getSocketId() {
        return socketId;
    }
}
