package com.example.mvvm_demo.base;

import io.socket.client.Socket;

public interface MvpView {
    void showLoading();

    void hideLoading();

    Socket getSocket();
}
