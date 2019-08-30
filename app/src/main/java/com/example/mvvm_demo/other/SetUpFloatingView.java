package com.example.mvvm_demo.other;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.WindowManager;

public class SetUpFloatingView {
    public static WindowManager.LayoutParams setUpViewClose() {
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT; //view width
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; //view height
        mLayoutParams.gravity = Gravity.BOTTOM;    //view gravity
        mLayoutParams.y = -100;    //view height position
        return AppUtils.setUpParams(mLayoutParams);
    }

    @SuppressLint("RtlHardcoded")
    public static WindowManager.LayoutParams setUpViewButton(){
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT; //view width
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; //view height
        mLayoutParams.gravity = Gravity.RIGHT | Gravity.TOP; //view right and top
        mLayoutParams.y = 200; //view height position
        return AppUtils.setUpParams(mLayoutParams);
    }
}
