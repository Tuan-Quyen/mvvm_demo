package com.example.mvvm_demo.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeUserHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemUser_btn)
    public CardView btn;
    @BindView(R.id.itemUser_tvName)
    public TextView tvName;
    @BindView(R.id.itemUser_tvSocketId)
    public TextView tvSocketId;

    public HomeUserHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
