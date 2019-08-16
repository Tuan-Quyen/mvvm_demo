package com.example.mvvm_demo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvm_demo.MyApplication;
import com.example.mvvm_demo.other.ProgressDialog;

import io.socket.client.Socket;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {
    private ProgressDialog progressBar;
    private Socket mSocket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSocket = ((MyApplication) getApplicationContext()).getSocket();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        if (progressBar == null) {
            progressBar = new ProgressDialog(this);
            progressBar.show();
        } else {
            progressBar.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.dismiss();
        }
    }
}
