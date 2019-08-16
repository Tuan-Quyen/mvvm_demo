package com.example.mvvm_demo.fragment.login;

import android.os.Handler;
import android.util.Log;

import com.example.mvvm_demo.base.BasePresenter;
import com.example.mvvm_demo.model.UserModel;
import com.example.mvvm_demo.other.ApiKeyParams;
import com.example.mvvm_demo.other.AppUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import io.socket.client.Socket;

public class LoginPresenterImp extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {
    private Socket mSocket;
    private Handler mHandler;

    LoginPresenterImp(Socket mSocket) {
        this.mSocket = mSocket;
    }

    void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void connectUser(String userName) {
        getView().showLoading();
        new Handler().postDelayed(() -> {
            if (mSocket.connected()) {
                mSocket.emit(ApiKeyParams.USER_LOGIN, userName);
                mSocket.on(ApiKeyParams.LIST_USER, args -> {
                    Log.d("data: ", args[0].toString());
                });
                getView().loginSuccess();
            } else {
                getView().hideLoading();
                mHandler.sendMessage(AppUtils.configMessage());
            }
        }, 3000);
    }
}
