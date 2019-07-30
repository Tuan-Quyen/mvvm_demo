package com.example.mvvm_demo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void addFragment(Fragment fragment, boolean addToBackStack, int resID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(resID, fragment);
        if (addToBackStack == true) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, int resID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(resID, fragment, fragment.getClass().getName());
        if (addToBackStack == true) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }
}
