package com.example.mvvm_demo.other;

import com.example.mvvm_demo.models.AudioModel;

public class Constant {
    static boolean isPlaying = false;
    static AudioModel audioModel;

    //key
    public final static String STATE_FLOATING_UI = "State_floating";
    public final static String STATE_MEDIA_MUSIC = "State_media_music";
    public final static String URL_SONG_MUSIC = "Url_Song_Music";
    //bundle
    public final static int REMOVE_FLOATING_UI = 1;
    public final static int SHOW_FLOATING_UI = 0;
    public final static int PLAY_MUSIC = 0;
    public final static int PAUSE_MUSIC = 1;
    public final static int STOP_MUSIC = 2;
    public final static int NEXT_MUSIC = 3;
    public final static int PREVIOUS_MUSIC = 4;

    public final static int REQUEST_CODE_VIEW_BUTTON = 0;
    public final static int REQUEST_CODE_VIEW_MUSIC = 1;
}
