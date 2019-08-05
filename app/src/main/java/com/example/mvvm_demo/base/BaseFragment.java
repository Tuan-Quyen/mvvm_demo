package com.example.mvvm_demo.base;

import androidx.fragment.app.Fragment;
import io.socket.client.Socket;

public class BaseFragment extends Fragment implements MvpView {

    public void addFragment(Fragment fragment, boolean addToBackStack, int resID) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).addFragment(fragment, addToBackStack, resID);
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, int resID) {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).replaceFragment(fragment, addToBackStack, resID);
        }
    }

    @Override
    public Socket getSocket() {
        if (getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity()).getSocket();
        }
        return null;
    }

    @Override
    public void showLoading() {
        if(getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if(getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).hideLoading();
        }
    }
}
