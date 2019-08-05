package com.example.mvvm_demo.fragment;

import com.example.mvvm_demo.base.MvpView;

public interface LoginContract {
    interface LoginView extends MvpView {
        void loginSuccess();

        void loginError(String error);
    }

    interface LoginPresenter{
        void connectUser(String userName);
    }
}
