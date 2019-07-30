package com.example.mvvm_demo.fragment.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.fragment.user.UserFragment;
import com.example.mvvm_demo.other.KeyboardUtils;
import com.example.mvvm_demo.other.ValidateType;
import com.example.mvvm_demo.other.ValidateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginFragment extends BaseFragment {
    @BindView(R.id.fragLogin_etEmail)
    EditText etEmail;
    @BindView(R.id.fragLogin_etPass)
    EditText etPass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        KeyboardUtils.setupUI(view, getActivity());
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragLogin_btnLogin)
    public void onClick() {
        validateLogin(etEmail.getText().toString().trim(), etPass.getText().toString().trim());
    }

    private void validateLogin(String email, String pass) {
        ValidateType validateType = ValidateUtils.getMessageValidate(email, pass);
        switch (validateType) {
            case EMPTY_EMAIL:
                etEmail.setBackground(getResources().getDrawable(R.drawable.shape_input_wrong_text_field));
                etEmail.setHint("Empty email!");
                etEmail.setHintTextColor(getResources().getColor(R.color.colorRed));
                etEmail.setText("");
                break;
            case EMPTY_PASSWORD:
                etPass.setBackground(getResources().getDrawable(R.drawable.shape_input_wrong_text_field));
                etPass.setHint("Empty password!");
                etEmail.setHintTextColor(getResources().getColor(R.color.colorRed));
                etPass.setText("");
                break;
            case PASSWORD_8_CHARS:
                etPass.setBackground(getResources().getDrawable(R.drawable.shape_input_wrong_text_field));
                etPass.setHint("Password is required 8 characters or more!");
                etEmail.setHintTextColor(getResources().getColor(R.color.colorRed));
                etPass.setText("");
                break;
            case UNFORMAT_EMAIL:
                etEmail.setBackground(getResources().getDrawable(R.drawable.shape_input_wrong_text_field));
                etEmail.setHint("Unavailable Email!");
                etEmail.setHintTextColor(getResources().getColor(R.color.colorRed));
                etEmail.setText("");
                break;
            default:
                etEmail.setBackground(getResources().getDrawable(R.drawable.shape_input_text_field));
                etPass.setBackground(getResources().getDrawable(R.drawable.shape_input_text_field));
                etEmail.setHint("Email");
                etPass.setHint("Password");
                etEmail.setHintTextColor(getResources().getColor(R.color.colorGrey));
                etEmail.setHintTextColor(getResources().getColor(R.color.colorGrey));
                replaceFragment(new UserFragment(), false, R.id.actMain_content);
                break;
        }
    }

    @OnTextChanged(R.id.fragLogin_etEmail)
    public void onTextChangeEmail(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            etEmail.setBackground(getResources().getDrawable(R.drawable.shape_input_text_field));
            etPass.setBackground(getResources().getDrawable(R.drawable.shape_input_text_field));
            etEmail.setHint("Email");
            etPass.setHint("Password");
            etEmail.setHintTextColor(getResources().getColor(R.color.colorGrey));
            etEmail.setHintTextColor(getResources().getColor(R.color.colorGrey));
        }
    }

    @OnTextChanged(R.id.fragLogin_etPass)
    public void onTextChangePass(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            etEmail.setBackground(getResources().getDrawable(R.drawable.shape_input_text_field));
            etPass.setBackground(getResources().getDrawable(R.drawable.shape_input_text_field));
            etEmail.setHint("Email");
            etPass.setHint("Password");
        }
    }
}
