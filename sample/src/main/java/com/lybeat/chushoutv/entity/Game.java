package com.lybeat.chushoutv.entity;

/**
 * Author: lybeat
 * Date: 2016/7/24
 */
public class Game {

    private String title;
    private int iconResId;
    private int tag;

    public Game() {}

    public Game(String title, int iconResId, int tag) {
        this.title = title;
        this.iconResId = iconResId;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
