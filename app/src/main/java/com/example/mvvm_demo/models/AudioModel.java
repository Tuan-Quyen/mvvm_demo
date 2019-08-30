package com.example.mvvm_demo.models;

public class AudioModel {
    private String titleMusic;
    private String singer;
    private String urlMusic;

    public AudioModel(String titleMusic, String singer, String urlMusic) {
        this.titleMusic = titleMusic;
        this.singer = singer;
        this.urlMusic = urlMusic;
    }

    public String getTitleMusic() {
        return titleMusic;
    }

    public String getSinger() {
        return singer;
    }

    public String getUrlMusic() {
        return urlMusic;
    }
}
