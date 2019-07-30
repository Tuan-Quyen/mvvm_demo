package com.example.mvvm_demo.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.adapter.UserAdapter;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.model.UserModel;
import com.example.mvvm_demo.other.AppUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends BaseFragment {
    @BindView(R.id.fragUser_rv)
    RecyclerView rv;
    private UserAdapter mAdapter;
    private List<UserModel> _data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        final UserViewModel userViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Objects.requireNonNull(getActivity()).getApplication()).create(UserViewModel.class);
        initView();
        observeViewModel(userViewModel);
        return view;
    }

    private void initView() {
        _data = new ArrayList<>();
        mAdapter = new UserAdapter(_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void observeViewModel(UserViewModel userViewModel) {
        userViewModel.getUserData().observe(getViewLifecycleOwner(), userResponse -> {
            if (userResponse.getError() != null){
                AppUtils.showDialog(getContext(),"Error",userResponse.getError());
            }else{
                _data.addAll(userResponse.getDataUser());
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
