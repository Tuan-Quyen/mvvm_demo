package com.example.mvvm_demo.other;

import android.annotation.SuppressLint;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

public class SetUpFloatingView {
    public static WindowManager.LayoutParams setUpViewClose(Display display) {
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.y = display.getHeight() - 200;
        return AppUtils.setUpParams(mLayoutParams);
    }

    @SuppressLint("RtlHardcoded")
    public static WindowManager.LayoutParams setUpViewButton(){
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.gravity = Gravity.RIGHT | Gravity.TOP;
        mLayoutParams.y = 200;
        return AppUtils.setUpParams(mLayoutParams);
    }
}
