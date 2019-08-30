package com.example.mvvm_demo.other;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.WindowManager;

public class AppUtils {
    public static void showDialog(Context context, String title, String content) {
        ErrorDialog errorDialog = new ErrorDialog(context, content, title);
        errorDialog.show();
    }

    static WindowManager.LayoutParams setUpParams(WindowManager.LayoutParams layoutParams){
        layoutParams.windowAnimations = WindowManager.LayoutParams.ROTATION_ANIMATION_CHANGED;
        layoutParams.format = PixelFormat.TRANSLUCENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        return layoutParams;
    }
}
