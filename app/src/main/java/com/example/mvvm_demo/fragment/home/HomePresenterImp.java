package com.example.mvvm_demo.fragment.home;

import android.util.Log;

import com.example.mvvm_demo.base.BasePresenter;
import com.example.mvvm_demo.model.UserModel;
import com.example.mvvm_demo.other.ApiKeyParams;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import io.socket.client.Socket;

public class HomePresenterImp extends BasePresenter<HomeContract.HomeView> implements HomeContract.HomePresenter {
    private Socket mSocket;

    HomePresenterImp(Socket mSocket) {
        this.mSocket = mSocket;
    }

    @Override
    public void getListUser() {
        if (mSocket.connected()) {
            mSocket.on(ApiKeyParams.LIST_USER, args -> {
                Log.d("data: ", args[0].toString());
                try {
                    JSONObject jsonObject = (JSONObject) args[0];
                    UserModel[] _data = new Gson().fromJson(jsonObject.getString("data"), UserModel[].class);
                    getView().getDataSuccess(Arrays.asList(_data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
