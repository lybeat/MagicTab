package com.lybeat.chushoutv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lybeat.magictab.MagicTab;

import java.util.List;

/**
 * Author: lybeat
 * Date: 2016/2/27
 */
public class TabAdapter extends FragmentPagerAdapter implements MagicTab.IconTabProvider {

    private List<Fragment> fragments;
    private List<String> titles;
    private List<Integer> noneIcons;
    private List<Integer> pressedIcons;

    public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public TabAdapter(FragmentManager fm, List<Fragment> fragments,
                      List<String> titles, List<Integer> noneIcons, List<Integer> pressedIcons) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
        this.noneIcons = noneIcons;
        this.pressedIcons = pressedIcons;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getPageNoneIconResId(int position) {
        return noneIcons.get(position);
    }

    @Override
    public int getPagePressedIconResId(int position) {
        return pressedIcons.get(position);
    }
}
