package com.example.mvvm_demo.fragment.home;

import android.util.Log;

import com.example.mvvm_demo.base.BasePresenter;
import com.example.mvvm_demo.model.UserModel;
import com.example.mvvm_demo.other.ApiKeyParams;

import io.socket.client.Socket;

public class HomePresenterImp extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {
    private Socket mSocket;

    public HomePresenterImp(Socket mSocket) {
        this.mSocket = mSocket;
    }

    @Override
    public void getListUser() {
        if (mSocket.connected()) {
            mSocket.on(ApiKeyParams.LIST_USER, args -> {
                Log.d("data: ", args[0].toString());
            });
        }
    }
}
