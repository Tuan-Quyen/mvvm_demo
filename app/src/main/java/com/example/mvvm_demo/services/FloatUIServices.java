package com.example.mvvm_demo.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.interfaces.PositionChangeListener;
import com.example.mvvm_demo.interfaces.PositionClickListener;
import com.example.mvvm_demo.models.AudioModel;
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
    private List<AudioModel> dataMusic;

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
        //check state of Floating UI , if activity is forgeground will not hide view.
        switch (state) {
            case Constant.SHOW_FLOATING_UI:
                initView();
                break;
            case Constant.REMOVE_FLOATING_UI:
                if (viewButton != null)
                    viewButton.setVisibility(View.GONE);
                if (viewMusic != null)
                    viewMusic.setVisibility(View.GONE);
                break;
        }
    }

    private void initView() {
        if(viewMusic != null){
            viewMusic.setVisibility(View.VISIBLE);
        }
        if (viewButton != null) {
            viewButton.setVisibility(View.VISIBLE);
        } else {
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            createViewIcon();
        }
    }

    @SuppressLint("RtlHardcoded")
    private void createViewIcon() {
        //get screen size display
        display = windowManager.getDefaultDisplay();
        //generate hardcore data music
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
        windowManager.addView(viewClose, SetUpFloatingView.setUpViewClose());
    }

    @Override
    public void onPositionChangeListener(View view, WindowManager.LayoutParams layoutParams, boolean isDragging) {
        //release view button inside close view will close service
        if (layoutParams.y > display.getHeight() - 300 && !isDragging) {
            windowManager.removeView(view);
            stopService(new Intent(this, FloatUIServices.class));
            stopService(new Intent(this, PlayMusicService.class));
        }
        //check is current button view is dragging will show view close at bottom
        if (isDragging) {
            viewClose.setVisibility(View.VISIBLE);
            FrameLayout frViewClose = viewClose.findViewById(R.id.viewClose_fr);
            if (layoutParams.y > display.getHeight() - 300) { //check current button view is dragging to view close will change color
                frViewClose.setBackground(getDrawable(R.drawable.shape_gradient_red_close));
            } else {
                frViewClose.setBackground(getDrawable(R.drawable.shape_gradient_dark_close));
            }
        } else {
            viewClose.setVisibility(View.GONE);
        }
        //on every pixel change on touch we will need call this to update current position view.
        windowManager.updateViewLayout(view, layoutParams);
    }

    @Override
    public void onPositionChangeMusicListener(View view, WindowManager.LayoutParams layoutParams) {
        windowManager.updateViewLayout(view, layoutParams);
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onPositionClickListener(WindowManager.LayoutParams layoutParams, View v) {
        int currentView = (int) v.getTag();
        switch (currentView) {
            case Constant.REQUEST_CODE_VIEW_BUTTON:
                //create view Music
                FloatingMusic floatingMusic = new FloatingMusic(this);
                //check current horizontal position is side left or side right
                if (layoutParams.x > display.getWidth() / 2) {//side left
                    layoutParams.x = layoutParams.x - 500; //show view Music and fix 500 pixel right
                }
                floatingMusic.setPosition(layoutParams); //set position to show view Music
                floatingMusic.setListener(this, this);
                floatingMusic.setDataMusic(dataMusic); //set data to view Music
                viewMusic = floatingMusic.getView();
                //show view Music
                windowManager.addView(viewMusic, layoutParams);
                break;
            case Constant.REQUEST_CODE_VIEW_MUSIC:
                //check current horizontal position is side left or side right of view Music
                if (layoutParams.x + 300 > display.getWidth() / 2) {//+300 pixel for current.x because view show right of view
                    layoutParams.x = display.getWidth() - 150;
                } else {
                    layoutParams.x = 0;
                }
                windowManager.addView(viewButton, layoutParams);
                break;
        }
        windowManager.removeView(v);
    }
}
