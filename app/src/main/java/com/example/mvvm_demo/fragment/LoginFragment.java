package com.example.mvvm_demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.other.AppUtils;
import com.example.mvvm_demo.other.ValidateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginContract.LoginView {
    @BindView(R.id.fragLogin_etUser)
    EditText etUser;
    private LoginPresenterImp presenterImp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        presenterImp = new LoginPresenterImp(getSocket());
        presenterImp.onAttach(this);
        return view;
    }

    @OnClick(R.id.fragLogin_btnLogin)
    public void onClick() {
        if (ValidateUtils.checkUserName(getContext(), etUser.getText().toString())) {
            presenterImp.connectUser(etUser.getText().toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterImp.onDetach();
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError(String error) {
        hideLoading();
        if (!getSocket().connected()) {
            AppUtils.showDialog(getContext(), getString(R.string.connection_error), error, () -> presenterImp.connectUser(etUser.getText().toString()));
        } else {
            AppUtils.showDialog(getContext(), getString(R.string.error_login), error, null);
        }
    }
}
