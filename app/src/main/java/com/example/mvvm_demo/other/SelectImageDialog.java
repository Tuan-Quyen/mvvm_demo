package com.example.mvvm_demo.other;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.interfaces.ChooseImageListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectImageDialog extends Dialog {
    private ChooseImageListener listener;

    public SelectImageDialog(@NonNull Context context, ChooseImageListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_image);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.dialogSelect_btnChooseImage, R.id.dialogSelect_btnOpenCamera})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.dialogSelect_btnChooseImage:
                if (listener != null) {
                    listener.onClickChooseImage();
                    dismiss();
                }
                break;
            case R.id.dialogSelect_btnOpenCamera:
                if (listener != null) {
                    listener.onClickOpenCamera();
                    dismiss();
                }
                break;
        }
    }
}
