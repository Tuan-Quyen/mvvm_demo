package com.example.mvvm_demo.connection;

import com.example.mvvm_demo.fragment.user.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("GetAllUser.php")
    Observable<UserResponse> getUser();
}
