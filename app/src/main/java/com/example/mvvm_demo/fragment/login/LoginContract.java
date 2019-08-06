package com.example.mvvm_demo.fragment.login;

import com.example.mvvm_demo.base.MvpView;

public interface LoginContract {
    interface LoginView extends MvpView {
        void loginSuccess();
    }

    interface LoginPresenter{
        void connectUser(String userName);
    }
}
