package com.example.mvvm_demo.fragment.user;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_demo.connection.ApiRequest;
import com.example.mvvm_demo.connection.ConfigConnection;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


//singleton pattern

public class UserRepository {
    private final ApiRequest apiRequest = ConfigConnection.getRetrofit().create(ApiRequest.class);
    private static UserRepository instantce;

    public synchronized static UserRepository getInstance(){
        if(instantce == null){
            instantce = new UserRepository();
        }
        return instantce;
    }

    MutableLiveData<UserResponse> getUserData(){
        final MutableLiveData<UserResponse> data = new MutableLiveData<>();
        Observable<UserResponse> getUser = apiRequest.getUser().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        getUser.subscribe(new Observer<UserResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserResponse userResponse) {
                data.setValue(userResponse);
            }

            @Override
            public void onError(Throwable e) {
                data.setValue(new UserResponse(e.getMessage()));
            }

            @Override
            public void onComplete() {

            }
        });
        return data;
    }
}
