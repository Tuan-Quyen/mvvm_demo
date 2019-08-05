package com.example.mvvm_demo.other;

import android.content.Context;
import android.text.TextUtils;

import com.example.mvvm_demo.R;

public class ValidateUtils {
    public static boolean checkUserName(Context context, String userName) {
        if (TextUtils.isEmpty(userName)) {
            AppUtils.showDialog(context, context.getString(R.string.error_login), "Your user name is empty!", null);
            return false;
        }
        if (userName.length() < 5) {
            AppUtils.showDialog(context, context.getString(R.string.error_login), "Your user name must be at least 6 chars!", null);
            return false;
        }
        return true;
    }
}
