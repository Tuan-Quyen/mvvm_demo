package com.example.mvvm_demo.base;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public void addFragment(Fragment fragment, boolean addToBackStack, int resID) {
        if(getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).addFragment(fragment,addToBackStack,resID);
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, int resID) {
        if(getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).replaceFragment(fragment,addToBackStack,resID);
        }
    }
}
