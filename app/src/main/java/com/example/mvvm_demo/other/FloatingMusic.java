package com.example.mvvm_demo.other;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.interfaces.PositionChangeListener;
import com.example.mvvm_demo.interfaces.PositionClickListener;
import com.example.mvvm_demo.models.AudioModel;
import com.example.mvvm_demo.services.PlayMusicService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatingMusic extends View implements View.OnTouchListener {
    @BindView(R.id.viewMusic_ivPlay)
    ImageView btnPlay;
    @BindView(R.id.viewMusic_ivPause)
    ImageView btnPause;
    @BindView(R.id.viewMusic_tvTitleMusic)
    TextView tvTitle;

    private float mStartX;
    private float mStartY;
    private float previousX;
    private float previousY;
    private WindowManager.LayoutParams mLayoutParams;
    private PositionChangeListener listener;
    private PositionClickListener clickListener;
    private View view;
    private List<AudioModel> dataMusic;
    private int currentPosition;
    private Intent intent;

    public FloatingMusic(Context context) {
        super(context);
        view = inflate(context, R.layout.view_music, new FrameLayout(context));
        view.setTag(Constant.REQUEST_CODE_VIEW_MUSIC);
        ButterKnife.bind(this, view);
        //draggable
        view.setOnTouchListener(this);
        //setup view
        initView(context);
    }

    public View getView() {
        return view;
    }

    public void setPosition(WindowManager.LayoutParams mLayoutParams) {
        this.mLayoutParams = mLayoutParams;
    }

    public void setListener(PositionChangeListener positionChangeListener, PositionClickListener clickListener) {
        this.listener = positionChangeListener;
        this.clickListener = clickListener;
    }

    public void setDataMusic(List<AudioModel> dataMusic) {
        this.dataMusic = dataMusic;
    }

    private void initView(Context context) {
        currentPosition = 0;
        tvTitle.setSelected(true);
        intent = new Intent(context, PlayMusicService.class);
        //check music isplaying
        if (Constant.isPlaying) {
            tvTitle.setText(getTitleSinger(Constant.audioModel));
            btnPause.setVisibility(VISIBLE);
            btnPlay.setVisibility(GONE);
        } else {
            btnPause.setVisibility(GONE);
            btnPlay.setVisibility(VISIBLE);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                previousX = mLayoutParams.x;
                previousY = mLayoutParams.y;

                mStartX = event.getRawX();
                mStartY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = event.getRawX() - mStartX;
                float deltaY = event.getRawY() - mStartY;
                mLayoutParams.x = (int) (previousX - deltaX);
                mLayoutParams.y = (int) (previousY + deltaY);
                listener.onPositionChangeMusicListener(view, mLayoutParams);
                break;
        }
        return false;
    }

    @OnClick({R.id.viewMusic_ivClose, R.id.viewMusic_ivPlay, R.id.viewMusic_ivPause, R.id.viewMusic_ivPrevious, R.id.viewMusic_ivStop, R.id.viewMusic_ivNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewMusic_ivClose:
                clickListener.onPositionClickListener(mLayoutParams, this.view);
                break;
            case R.id.viewMusic_ivPlay:
                btnPause.setVisibility(VISIBLE);
                btnPlay.setVisibility(GONE);
                intent.putExtra(Constant.STATE_MEDIA_MUSIC, Constant.PLAY_MUSIC);
                intent.putExtra(Constant.URL_SONG_MUSIC, dataMusic.get(currentPosition).getUrlMusic());
                view.getContext().startService(intent);
                tvTitle.setText(getTitleSinger(dataMusic.get(currentPosition)));
                Constant.audioModel = dataMusic.get(currentPosition);
                Constant.isPlaying = true;
                break;
            case R.id.viewMusic_ivPause:
                btnPause.setVisibility(GONE);
                btnPlay.setVisibility(VISIBLE);
                intent.putExtra(Constant.STATE_MEDIA_MUSIC, Constant.PAUSE_MUSIC);
                view.getContext().startService(intent);
                Constant.isPlaying = false;
                break;
            case R.id.viewMusic_ivStop:
                btnPause.setVisibility(GONE);
                btnPlay.setVisibility(VISIBLE);
                intent.putExtra(Constant.STATE_MEDIA_MUSIC, Constant.STOP_MUSIC);
                view.getContext().startService(intent);
                Constant.isPlaying = false;
                tvTitle.setText(view.getContext().getString(R.string.music_player));
                break;
            case R.id.viewMusic_ivPrevious:
                if (currentPosition == 0) {
                    currentPosition = dataMusic.size() - 1;
                } else {
                    currentPosition--;
                }
                intent.putExtra(Constant.STATE_MEDIA_MUSIC, Constant.PREVIOUS_MUSIC);
                intent.putExtra(Constant.URL_SONG_MUSIC, dataMusic.get(currentPosition).getUrlMusic());
                view.getContext().startService(intent);
                tvTitle.setText(getTitleSinger(dataMusic.get(currentPosition)));
                Constant.audioModel = dataMusic.get(currentPosition);
                break;
            case R.id.viewMusic_ivNext:
                if (currentPosition == dataMusic.size() - 1) {
                    currentPosition = 0;
                } else {
                    currentPosition++;
                }
                intent.putExtra(Constant.STATE_MEDIA_MUSIC, Constant.NEXT_MUSIC);
                intent.putExtra(Constant.URL_SONG_MUSIC, dataMusic.get(currentPosition).getUrlMusic());
                view.getContext().startService(intent);
                tvTitle.setText(getTitleSinger(dataMusic.get(currentPosition)));
                Constant.audioModel = dataMusic.get(currentPosition);
                break;
        }
    }

    private String getTitleSinger(AudioModel audioModel) {
        return audioModel.getTitleMusic() + " - " + audioModel.getSinger();
    }
}
