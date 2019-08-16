package com.example.mvvm_demo;

import android.app.Application;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;

public class MyApplication extends Application {
    private static Socket mSocket;

    {
        try {
            mSocket = IO.socket("https://pure-shore-49093.herokuapp.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        mSocket.connect();
        return mSocket;
    }
}
