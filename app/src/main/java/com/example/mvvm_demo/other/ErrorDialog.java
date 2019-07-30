package com.example.mvvm_demo.other;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mvvm_demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErrorDialog extends Dialog {
    @BindView(R.id.dialogError_tvContent)
    TextView tvContent;
    @BindView(R.id.dialogError_tvTitle)
    TextView tvTitle;
    private String content;
    private String title;

    public ErrorDialog(@NonNull Context context, String content, String title) {
        super(context);
        this.content = content;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_error);
        ButterKnife.bind(this);
        tvContent.setText(content);
        tvTitle.setText(title);
        setCanceledOnTouchOutside(false);
        if (getWindow() != null) {
            getWindow().setDimAmount(0.5f);
        }
    }

    @OnClick(R.id.dialogError_btnOk)
    public void onClick(){
        dismiss();
    }
}
