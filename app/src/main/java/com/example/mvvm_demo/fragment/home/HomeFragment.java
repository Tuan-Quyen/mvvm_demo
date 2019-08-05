package com.example.mvvm_demo.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseFragment;

import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {
    private HomePresenterImp presenterImp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        presenterImp = new HomePresenterImp(getSocket());
        presenterImp.onAttach(this);
        Log.e("Socket: ", String.valueOf(getSocket().connected()));
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterImp.onDetach();
    }

    @Override
    public void getDataSuccess() {

    }

    @Override
    public void getDataError(String error) {

    }
}
