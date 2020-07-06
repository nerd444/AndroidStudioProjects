package com.nerd.youtube.model;

import java.io.Serializable;

public class Youtube implements Serializable {
    private String title;
    private String description;
    private String videoId;
    private String url;

    public Youtube(){

    }

    public Youtube(String title, String description, String videoId, String url) {
        this.title = title;
        this.description = description;
        this.videoId = videoId;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
