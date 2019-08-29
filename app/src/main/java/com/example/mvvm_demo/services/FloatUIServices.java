package com.example.mvvm_demo.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.interfaces.PositionChangeListener;
import com.example.mvvm_demo.interfaces.PositionClickListener;
import com.example.mvvm_demo.other.Constant;
import com.example.mvvm_demo.other.FloatingButton;
import com.example.mvvm_demo.other.FloatingMusic;
import com.example.mvvm_demo.other.GenerateDataUtils;
import com.example.mvvm_demo.other.SetUpFloatingView;

import java.util.List;

public class FloatUIServices extends Service implements PositionChangeListener, PositionClickListener {
    private WindowManager windowManager;
    private View viewButton, viewClose, viewMusic;
    private Display display;
    private List<String> dataMusic;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        checkData(intent);
        return START_STICKY;
    }

    private void checkData(Intent intent) {
        if (intent == null) {
            return;
        }
        int state = intent.getExtras().getInt(Constant.STATE_FLOATING_UI);
        switch (state) {
            case Constant.SHOW_FLOATING_UI:
                initView();
                break;
            case Constant.REMOVE_FLOATING_UI:
                if (viewButton != null)
                    viewButton.setVisibility(View.GONE);
                break;
        }
    }

    private void initView() {
        if (viewButton != null) {
            viewButton.setVisibility(View.VISIBLE);
        } else {
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            createViewIcon();
        }
    }

    @SuppressLint("RtlHardcoded")
    private void createViewIcon() {
        display = windowManager.getDefaultDisplay();
        dataMusic = GenerateDataUtils.genListMusic();

        //setup view button
        FloatingButton floatingButton = new FloatingButton(this);
        floatingButton.setPosition(display, SetUpFloatingView.setUpViewButton());
        floatingButton.setListener(this, this);
        viewButton = floatingButton.getView();
        windowManager.addView(viewButton, SetUpFloatingView.setUpViewButton());

        //setup view close bottom
        viewClose = View.inflate(this, R.layout.view_close, new FrameLayout(this));
        viewClose.setVisibility(View.GONE);
        windowManager.addView(viewClose, SetUpFloatingView.setUpViewClose(display));
    }

    @Override
    public void onPositionChangeListener(View view, WindowManager.LayoutParams layoutParams, boolean isDragging) {
        if (layoutParams.y > display.getHeight() - 300 && !isDragging) {
            windowManager.removeView(view);
            stopSelf();
        }
        if (isDragging) {
            viewClose.setVisibility(View.VISIBLE);
            FrameLayout frViewClose = viewClose.findViewById(R.id.viewClose_fr);
            if (layoutParams.y > display.getHeight() - 300) {
                frViewClose.setBackground(getDrawable(R.drawable.shape_gradient_red_close));
            } else {
                frViewClose.setBackground(getDrawable(R.drawable.shape_gradient_dark_close));
            }
        } else {
            viewClose.setVisibility(View.GONE);
        }
        windowManager.updateViewLayout(view, layoutParams);
    }

    @Override
    public void onPositionChangeMusicListener(View view, WindowManager.LayoutParams layoutParams) {
        windowManager.updateViewLayout(view, layoutParams);
    }

    @Override
    public void onPositionClickListener(WindowManager.LayoutParams layoutParams, View v) {
        int currentView = (int) v.getTag();
        switch (currentView) {
            case Constant.REQUEST_CODE_VIEW_BUTTON:
                FloatingMusic floatingMusic = new FloatingMusic(this);
                floatingMusic.setPosition(layoutParams);
                floatingMusic.setListener(this, this);
                floatingMusic.setDataMusic(dataMusic);
                viewMusic = floatingMusic.getView();
                windowManager.addView(viewMusic, layoutParams);
                break;
            case Constant.REQUEST_CODE_VIEW_MUSIC:
                windowManager.addView(viewButton, layoutParams);
                break;
        }
        windowManager.removeView(v);
    }
}
