package com.example.mvvm_demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.model.UserModel;
import com.example.mvvm_demo.viewholder.UserHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    private List<UserModel> data;

    public UserAdapter(List<UserModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.tvName.setText(data.get(position).getUsername());
        holder.tvPhone.setText(data.get(position).getPhone());
        if(data.get(position).getGender().equalsIgnoreCase("1")){
            holder.rbtnMale.setChecked(true);
        }else{
            holder.rbtnFemale.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }
}
