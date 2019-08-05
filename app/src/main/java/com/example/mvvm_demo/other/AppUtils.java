package com.example.mvvm_demo.other;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.interfaces.ConfirmListener;

public class AppUtils {
    public static void showDialog(Context context, String title, String content, @Nullable ConfirmListener listener) {
        ErrorDialog errorDialog = new ErrorDialog(context, content, title, listener);
        errorDialog.show();
    }
}
