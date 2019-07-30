package com.example.mvvm_demo.other;

import android.content.Context;

public class AppUtils {
    public static void showDialog(Context context,String title, String content){
        ErrorDialog errorDialog = new ErrorDialog(context,content,title);
        errorDialog.show();
    }
}
