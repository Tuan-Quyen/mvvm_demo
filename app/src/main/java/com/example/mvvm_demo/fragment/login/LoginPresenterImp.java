package com.example.mvvm_demo.fragment.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.mvvm_demo.base.BasePresenter;
import com.example.mvvm_demo.other.ApiKeyParams;
import com.example.mvvm_demo.other.Constant;

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
        if (!mSocket.connected()) {
            mSocket.connect();
        }
        new Handler().postDelayed(() -> {
            if (mSocket.connected()) {
                mSocket.emit(ApiKeyParams.USER_LOGIN, userName);
                getView().loginSuccess();
            } else {
                getView().hideLoading();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.MESSAGE_THREAD_LOGIN, "No Connection!Trying to reconnect");
                message.what = Constant.MESSAGE_THREAD_LOGIN_WHAT;
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        }, 3000);
    }
}
