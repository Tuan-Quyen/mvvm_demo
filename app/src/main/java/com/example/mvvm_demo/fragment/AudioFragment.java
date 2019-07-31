package com.example.mvvm_demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.other.Constant;
import com.example.mvvm_demo.other.KeyboardUtils;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class AudioFragment extends BaseFragment {
    @BindView(R.id.fragAudio_tv)
    TextView tv;
    @BindView(R.id.fragAudio_etInput)
    EditText et;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio_text,container,false);
        ButterKnife.bind(this,view);
        KeyboardUtils.setupUI(view,getActivity());
        return view;
    }

    @OnClick({R.id.fragAudio_btnRecord,R.id.fragAudio_btnSend})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.fragAudio_btnRecord:
                openMic();
                break;
            case R.id.fragAudio_btnSend:
                break;
        }
    }

    private void openMic(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);

        startActivityForResult(intent, Constant.REQ_CODE_SPEECH_OUTPUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.REQ_CODE_SPEECH_OUTPUT:
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> voiceTextData = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    et.setText(voiceTextData.get(0));
                }
                break;
        }
    }
}
