package com.example.mvvm_demo.fragment.home;

import com.example.mvvm_demo.base.MvpView;

public interface HomeContract {
    interface HomeView extends MvpView {
        void getDataSuccess();

        void getDataError(String error);
    }

    interface HomePresenter{
        void getListUser();
    }
}