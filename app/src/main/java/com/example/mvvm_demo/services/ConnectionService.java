package com.example.mvvm_demo.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ConnectionService extends Service {
    private final IBinder socketBinder = new SocketBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return socketBinder;
    }

    public class SocketBinder extends Binder {
        // Now you have access to all your Service's public methods
        public ConnectionService getService() {
            return ConnectionService.this;
        }
    }
}

