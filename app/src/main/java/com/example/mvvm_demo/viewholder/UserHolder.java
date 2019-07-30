package com.example.mvvm_demo.viewholder;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.itemUser_tvName)
    public TextView tvName;
    @BindView(R.id.itemUser_tvPhone)
    public TextView tvPhone;
    @BindView(R.id.itemUser_rbtnMale)
    public RadioButton rbtnMale;
    @BindView(R.id.itemUser_rbtnFemale)
    public RadioButton rbtnFemale;

    public UserHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
