package com.example.mvvm_demo.other;

import android.text.TextUtils;

public class ValidateUtils {
    public static ValidateType getMessageValidate(String email,String pass) {
        if (TextUtils.isEmpty(email)) {
            return ValidateType.EMPTY_EMAIL;
        }
        if (TextUtils.isEmpty(pass)) {
            return ValidateType.EMPTY_PASSWORD;
        }
        if (pass.length() < 7) {
            return ValidateType.PASSWORD_8_CHARS;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidateType.UNFORMAT_EMAIL;
        }
        return ValidateType.CORRECT;
    }
}
