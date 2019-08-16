package com.example.mvvm_demo.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.adapter.HomeUserAdapter;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.interfaces.ItemClickListener;
import com.example.mvvm_demo.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeContract.HomeView, ItemClickListener {
    @BindView(R.id.fragHome_rv)
    RecyclerView rvUser;

    private List<UserModel> _dataUser;
    private HomePresenterImp presenterImp;
    private HomeUserAdapter adapter;
    private Handler mHandler;

    public HomeFragment(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        presenterImp = new HomePresenterImp(getSocket());
        presenterImp.setmHandler(mHandler);
        presenterImp.onAttach(this);
        initView();
        return view;
    }

    private void initView() {
        //getUser
        presenterImp.getListUser();

        _dataUser = new ArrayList<>();
        LinearLayoutManager linear = new LinearLayoutManager(getContext());
        adapter = new HomeUserAdapter(_dataUser, this);
        rvUser.setLayoutManager(linear);
        rvUser.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterImp.onDetach();
    }

    @Override
    public void getDataSuccess(List<UserModel> _data) {
        _dataUser.addAll(_data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getDataError(String error) {

    }

    @Override
    public void onClickListener(int position) {
        Log.e("Name: ", _dataUser.get(position).getName());
    }
}
