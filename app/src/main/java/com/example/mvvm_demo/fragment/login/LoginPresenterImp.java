package com.example.mvvm_demo.fragment.login;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.mvvm_demo.base.BasePresenter;
import com.example.mvvm_demo.other.ApiKeyParams;

import org.json.JSONException;
import org.json.JSONObject;

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
                getView().loginSuccess();
            }
        },2000);
    }

}
