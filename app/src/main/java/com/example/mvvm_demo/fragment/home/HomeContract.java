package com.example.mvvm_demo.fragment.home;

import com.example.mvvm_demo.base.MvpView;
import com.example.mvvm_demo.model.UserModel;

import java.util.List;

public interface HomeContract {
    interface HomeView extends MvpView {
        void getDataSuccess(List<UserModel> _data);

        void getDataError(String error);
    }

    interface HomePresenter{
        void getListUser();
    }
}