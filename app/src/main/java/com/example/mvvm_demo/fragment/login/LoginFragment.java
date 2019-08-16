package com.example.mvvm_demo.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.activity.MainActivity;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.interfaces.ConfirmListener;
import com.example.mvvm_demo.other.AppUtils;
import com.example.mvvm_demo.other.ValidateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginContract.LoginView, ConfirmListener {
    @BindView(R.id.fragLogin_etUser)
    EditText etUser;
    private LoginPresenterImp presenterImp;
    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        presenterImp = new LoginPresenterImp(getSocket());
        presenterImp.onAttach(this);
        initView();
        return view;
    }

    private void initView() {
        mHandler = AppUtils.handleMessage(this);
        presenterImp.setmHandler(mHandler);
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
        mHandler = null;
    }

    @Override
    public void loginSuccess() {
        hideLoading();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfirmListener(String msg) {
        AppUtils.showDialog(getContext(), getString(R.string.connection_error),
                msg, msg1 -> {
                    getSocket().connect();
                    presenterImp.connectUser(etUser.getText().toString());
                });
    }
}
