package com.lybeat.chushoutv.entity;

/**
 * Author: lybeat
 * Date: 2016/7/24
 */
public class Item {

    private String title;
    private String subtitle;
    private boolean active;

    public Item() {

    }

    public Item(String title, String subTitle, boolean active) {
        this.title = title;
        this.subtitle = subTitle;
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
