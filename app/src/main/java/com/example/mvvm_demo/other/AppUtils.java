package com.example.mvvm_demo.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import com.example.mvvm_demo.interfaces.ConfirmListener;

public class AppUtils {
    public static void showDialog(Context context, String title, String content, @Nullable ConfirmListener listener) {
        ErrorDialog errorDialog = new ErrorDialog(context, content, title, listener);
        errorDialog.show();
    }

    @SuppressLint("HandlerLeak")
    public static Handler handleMessage(ConfirmListener listener) {
        return new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constant.MESSAGE_THREAD_LOGIN_WHAT:
                        if (listener != null) {
                            listener.onConfirmListener(msg.getData().getString(Constant.MESSAGE_THREAD_LOGIN));
                        }
                        removeMessages(msg.what);
                        break;
                }
            }
        };
    }

    public static Message configMessage() {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.MESSAGE_THREAD_LOGIN, "No Connection!Trying to reconnect");
        message.what = Constant.MESSAGE_THREAD_LOGIN_WHAT;
        message.setData(bundle);
        return message;
    }
}
