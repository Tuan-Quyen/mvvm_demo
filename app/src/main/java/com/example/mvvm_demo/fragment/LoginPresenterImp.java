package com.example.mvvm_demo.fragment;

import android.os.Handler;
import android.util.Log;

import com.example.mvvm_demo.base.BasePresenter;
import com.example.mvvm_demo.other.ApiKeyParams;

import io.socket.client.Socket;

public class LoginPresenterImp extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {
    private Socket mSocket;

    LoginPresenterImp(Socket mSocket) {
        this.mSocket = mSocket;
    }

    @Override
    public void connectUser(String userName) {
        getView().showLoading();
        if (!mSocket.connected()) {
            mSocket.connect();
        }
        new Handler().postDelayed(() -> {
            if (mSocket.connected()) {
                mSocket.emit(ApiKeyParams.USER_LOGIN, userName);
                mSocket.on(ApiKeyParams.EXIST_USER, args -> {
                    boolean isExist = (Boolean) args[0];
                    if (isExist) {
                        getView().loginError("User name is existed");
                    }
                    getView().hideLoading();
                });
                /*mSocket.on(ApiKeyParams.LIST_USER, args -> {

                    Log.d("data: ", args[0].toString());
                });*/
            } else {
                getView().loginError("Connection has error!Trying to reconnect");
            }
        }, 2000);
    }
}
