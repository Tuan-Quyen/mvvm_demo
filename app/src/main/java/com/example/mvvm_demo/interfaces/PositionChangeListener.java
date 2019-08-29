package com.example.mvvm_demo.interfaces;

import android.view.View;
import android.view.WindowManager;

public interface PositionChangeListener {
    void onPositionChangeListener(View view, WindowManager.LayoutParams layoutParams, boolean isDragging);

    void onPositionChangeMusicListener(View view, WindowManager.LayoutParams layoutParams);
}
