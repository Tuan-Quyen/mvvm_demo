package com.example.mvvm_demo.other;

import android.content.Context;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.interfaces.PositionChangeListener;
import com.example.mvvm_demo.interfaces.PositionClickListener;

public class FloatingButton extends View implements View.OnTouchListener, View.OnClickListener {
    private float mStartX;
    private float mStartY;
    private float previousX;
    private float previousY;
    private Display display;
    private WindowManager.LayoutParams mLayoutParams;
    private PositionChangeListener listener;
    private PositionClickListener clickListener;
    private View view;

    public FloatingButton(Context context) {
        super(context);
        view = inflate(context, R.layout.view_icon, new FrameLayout(context));
        view.setTag(Constant.REQUEST_CODE_VIEW_BUTTON);
        //draggable
        view.setOnTouchListener(this);
        //click button
        view.setOnClickListener(this);
    }

    public View getView() {
        return view;
    }

    public void setPosition(Display display, WindowManager.LayoutParams mLayoutParams) {
        this.display = display;
        this.mLayoutParams = mLayoutParams;
    }

    public void setListener(PositionChangeListener positionChangeListener, PositionClickListener clickListener) {
        this.listener = positionChangeListener;
        this.clickListener = clickListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float screenWith = display.getWidth();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                previousX = mLayoutParams.x;
                previousY = mLayoutParams.y;

                mStartX = event.getRawX();
                mStartY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (mLayoutParams.x < screenWith / 2) {
                    mLayoutParams.x = 0;
                } else {
                    mLayoutParams.x = (int) screenWith - 150;
                }
                listener.onPositionChangeListener(view, mLayoutParams, false);
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = event.getRawX() - mStartX;
                float deltaY = event.getRawY() - mStartY;
                mLayoutParams.x = (int) (previousX - deltaX);
                mLayoutParams.y = (int) (previousY + deltaY);
                listener.onPositionChangeListener(view, mLayoutParams, true);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        clickListener.onPositionClickListener(mLayoutParams, v);
    }
}
