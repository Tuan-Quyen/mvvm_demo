package com.example.mvvm_demo.base;

public interface IPresenter <V extends MvpView> {
    void onAttach(V mvpView);

    void onDetach();

    boolean isViewAttach();
}