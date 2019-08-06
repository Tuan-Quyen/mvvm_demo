package com.example.mvvm_demo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.adapter.holder.HomeUserHolder;
import com.example.mvvm_demo.interfaces.ItemClickListener;
import com.example.mvvm_demo.model.UserModel;

import java.util.List;

public class HomeUserAdapter extends RecyclerView.Adapter<HomeUserHolder> {
    private List<UserModel> _data;
    private ItemClickListener listener;

    public HomeUserAdapter(List<UserModel> _data, ItemClickListener listener) {
        this._data = _data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new HomeUserHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomeUserHolder holder, int position) {
        holder.tvName.setText(_data.get(position).getName());
        holder.tvSocketId.setText("Socket id: " + _data.get(position).getSocketId());
        holder.btn.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _data != null ? _data.size() : 0;
    }
}
