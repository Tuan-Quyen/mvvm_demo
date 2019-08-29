package com.example.mvvm_demo.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.mvvm_demo.other.Constant;

import java.io.IOException;

public class PlayMusicService extends Service {
    MediaPlayer player;
    boolean hasSong = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        player.setLooping(true);
        player.setVolume(100, 100);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initMedia(intent);
        return START_STICKY;
    }

    private void initMedia(Intent intent) {
        int stateMedia = intent.getExtras().getInt(Constant.STATE_MEDIA_MUSIC);
        switch (stateMedia) {
            case Constant.PLAY_MUSIC:
                if (!hasSong) {
                    try {
                        player.setDataSource(intent.getExtras().getString(Constant.URL_SONG_MUSIC));
                        player.prepare();
                        player.start();
                        hasSong = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    player.start();
                }
                Log.e("State Music", "Play");
                break;
            case Constant.PAUSE_MUSIC:
                Log.e("State Music", "Pause");
                player.pause();
                break;
            case Constant.STOP_MUSIC:
                Log.e("State Music", "Stop");
                if (player.isPlaying()) {
                    player.stop();
                    player.reset();
                    player.release();
                    hasSong = false;
                    stopSelf();
                }
                break;
            case Constant.NEXT_MUSIC:
                startOtherMusic(intent.getExtras().getString(Constant.URL_SONG_MUSIC));
                Log.e("State Music", "Next");
                break;
            case Constant.PREVIOUS_MUSIC:
                startOtherMusic(intent.getExtras().getString(Constant.URL_SONG_MUSIC));
                Log.e("State Music", "Previous");
                break;
        }
    }

    private void startOtherMusic(String url) {
        player.stop();
        player.reset();
        try {
            player.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
