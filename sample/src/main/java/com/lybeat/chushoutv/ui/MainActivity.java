package com.lybeat.chushoutv.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lybeat.chushoutv.R;
import com.lybeat.chushoutv.adapter.TabAdapter;
import com.lybeat.magictab.MagicTab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        MagicTab magicTab = (MagicTab) findViewById(R.id.pager_tab_strip);

        HomeFragment homeFragment = new HomeFragment();
        FollowFragment followFragment = new FollowFragment();
        ZoneFragment zoneFragment = new ZoneFragment();
        MineFragment mineFragment = new MineFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(followFragment);
        fragments.add(zoneFragment);
        fragments.add(mineFragment);

        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("关注");
        titles.add("专区");
        titles.add("我的");

        List<Integer> noneIcons = new ArrayList<>();
        noneIcons.add(R.drawable.tab_icon_homemain_n);
        noneIcons.add(R.drawable.tab_icon_subcribe_n);
        noneIcons.add(R.drawable.tab_icon_zone_n);
        noneIcons.add(R.drawable.tab_icon_mine_n);

        List<Integer> pressedIcons = new ArrayList<>();
        pressedIcons.add(R.drawable.tab_icon_homemain_p);
        pressedIcons.add(R.drawable.tab_icon_subcribe_p);
        pressedIcons.add(R.drawable.tab_icon_zone_p);
        pressedIcons.add(R.drawable.tab_icon_mine_p);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments, titles, noneIcons, pressedIcons);
        viewPager.setAdapter(tabAdapter);
        magicTab.setViewPager(viewPager);
        magicTab.setMode(MagicTab.MODE_BLANK);
        magicTab.setBlankIndex(2);

        ImageView blankImg = (ImageView) findViewById(R.id.tab_blank_img);
        blankImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hi, I'am not magic tab", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
