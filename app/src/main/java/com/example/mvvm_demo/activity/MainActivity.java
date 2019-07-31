package com.example.mvvm_demo.activity;

import android.os.Bundle;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseActivity;
import com.example.mvvm_demo.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new MainFragment(),false,R.id.actMain_content);
    }
}
