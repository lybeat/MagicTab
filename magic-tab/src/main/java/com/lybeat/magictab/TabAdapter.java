package com.lybeat.magictab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/27
 */
public class TabAdapter extends FragmentPagerAdapter implements TabIcon {

    private List<Tab> tabs;

    public TabAdapter(FragmentManager fm, List<Tab> tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public int getPageNormalIconId(int position) {
        return tabs.get(position).getNormalIconId();
    }

    @Override
    public int getPagePressedIconId(int position) {
        return tabs.get(position).getPressedIconId();
    }
}
