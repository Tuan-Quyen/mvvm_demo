package com.example.mvvm_demo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseActivity;
import com.example.mvvm_demo.other.Constant;
import com.example.mvvm_demo.services.FloatUIServices;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        intent = new Intent(this, FloatUIServices.class);
    }

    @OnClick(R.id.btnService)
    public void onClick() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent.putExtra(Constant.STATE_FLOATING_UI, Constant.REMOVE_FLOATING_UI);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        intent.putExtra(Constant.STATE_FLOATING_UI, Constant.SHOW_FLOATING_UI);
        startService(intent);
    }
}
