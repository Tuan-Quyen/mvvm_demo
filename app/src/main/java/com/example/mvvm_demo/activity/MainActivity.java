package com.example.mvvm_demo.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseActivity;
import com.example.mvvm_demo.fragment.home.HomeFragment;
import com.example.mvvm_demo.interfaces.ConfirmListener;
import com.example.mvvm_demo.other.AppUtils;

public class MainActivity extends BaseActivity implements ConfirmListener {
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = AppUtils.handleMessage(this);
        replaceFragment(new HomeFragment(mHandler), false, R.id.actMain_content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfirmListener(@Nullable String msg) {
        AppUtils.showDialog(this, getString(R.string.connection_error),
                msg, msg1 -> checkConnection());
    }

    private void checkConnection() {
        new Handler().postDelayed(() -> {
            if (!getSocket().connected()) {
                getSocket().connect();
                mHandler.sendMessage(AppUtils.configMessage());
            }
        }, 3000);
    }
}
