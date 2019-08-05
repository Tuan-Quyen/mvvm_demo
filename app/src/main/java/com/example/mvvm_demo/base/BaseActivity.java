package com.example.mvvm_demo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvm_demo.other.ProgressDialog;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {
    private Socket mSocket;

    {
        try {
            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            opts.reconnectionDelay = 1000;
            opts.reconnectionDelayMax = 3000;
            opts.reconnectionAttempts = 3;
            mSocket = IO.socket("https://pure-shore-49093.herokuapp.com",opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSocket.connected()) {
            mSocket.disconnect();
        }
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, int resID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(resID, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, int resID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(resID, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    @Override
    public Socket getSocket() {
        return mSocket;
    }

    @Override
    public void showLoading() {
        if(progressBar == null){
            progressBar = new ProgressDialog(this);
            progressBar.show();
        }else{
            progressBar.show();
        }
    }

    @Override
    public void hideLoading() {
        if(progressBar != null){
            progressBar.dismiss();
        }
    }
}
