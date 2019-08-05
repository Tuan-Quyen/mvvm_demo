package com.example.mvvm_demo.activity;

import android.os.Bundle;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseActivity;
import com.example.mvvm_demo.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment(), true, R.id.actMain_content);
    }
}
