package com.lybeat.magictab;

import android.support.v4.app.Fragment;

/**
 * Author: lybeat
 * Date: 2016/8/17
 */
public class Tab {

    private Fragment fragment;
    private String title;
    private int normalIconId;
    private int pressedIconId;

    public Tab() {}

    public Tab(Fragment fragment, String title, int normalIconId, int pressedIconId) {
        this.fragment = fragment;
        this.title = title;
        this.normalIconId = normalIconId;
        this.pressedIconId = pressedIconId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNormalIconId() {
        return normalIconId;
    }

    public void setNormalIconId(int normalIconId) {
        this.normalIconId = normalIconId;
    }

    public int getPressedIconId() {
        return pressedIconId;
    }

    public void setPressedIconId(int pressedIconId) {
        this.pressedIconId = pressedIconId;
    }
}
