package com.example.mvvm_demo.fragment.user;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class UserViewModel extends AndroidViewModel {
    private final LiveData<UserResponse> mDataUser;
    private UserRepository userRepository = UserRepository.getInstance();

    public UserViewModel(Application application) {
        super(application);
        mDataUser = userRepository.getUserData();
    }

    LiveData<UserResponse> getUserData(){
        return mDataUser;
    }
}
