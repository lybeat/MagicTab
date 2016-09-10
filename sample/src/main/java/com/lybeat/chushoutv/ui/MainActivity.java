package com.lybeat.chushoutv.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lybeat.chushoutv.R;
import com.lybeat.magictab.MagicTab;
import com.lybeat.magictab.Tab;
import com.lybeat.magictab.TabAdapter;

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

        List<Tab> tabs = new ArrayList<>();
        tabs.add(new Tab(new HomeFragment(), "首页", R.drawable.tab_icon_homemain_n, R.drawable.tab_icon_homemain_p));
        tabs.add(new Tab(new FollowFragment(), "关注", R.drawable.tab_icon_subcribe_n, R.drawable.tab_icon_subcribe_p));
        tabs.add(new Tab(new ZoneFragment(), "专区", R.drawable.tab_icon_zone_n, R.drawable.tab_icon_zone_p));
        tabs.add(new Tab(new MineFragment(), "我的", R.drawable.tab_icon_mine_n, R.drawable.tab_icon_mine_p));

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), tabs);
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
