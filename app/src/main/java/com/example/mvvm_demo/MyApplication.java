package com.example.mvvm_demo;

import android.app.Application;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MyApplication extends Application {
    private Socket mSocket;

    {
        try {
            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            opts.reconnectionDelay = 1000;
            opts.reconnectionDelayMax = 3000;
            opts.reconnectionAttempts = 3;
            mSocket = IO.socket("https://pure-shore-49093.herokuapp.com", opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return mSocket;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mSocket = null;
    }
}
