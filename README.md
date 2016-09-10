## MagicTab
MagicTab is a PagerTabStrip.

## Feature
1. Support two mode, MODE_NORMAL and MODE_BLANK. In MODE_BLANK you can insert a blank view to MagicTab.
2. Support custom MagicTab background and the tab's text color and size.

## Demo
### MODE_NORMAL:
![MODE_NORMAL](https://github.com/lybeat/MagicTab/blob/master/screenshot/magic_tab_normal.gif?raw=true)

### MODE_BLANK:
![MODE_BLANK](https://github.com/lybeat/MagicTab/blob/master/screenshot/magic_tab_blank.gif?raw=true)

## Gradle Dependency
    compile 'com.lybeat:magic-tab:1.1.1'

## Usage
* ### STEP1:
    Introduced the MagicTab to your layout file

    ```XML
    <com.lybeat.magictab.MagicTab
        android:id="@+id/pager_tab_strip"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        lybeat:ptsPadding="4dp"
        lybeat:ptsBackground="@color/white"
        lybeat:ptsTextColorNormal="#ff666666"
        lybeat:ptsTextColorPressed="#ffff5959"
        lybeat:ptsTextSize="12sp"/>
    ```
* ### STEP2:
    Create tabs. Tab is provided MagicTab.

    ```Java
    List<Tab> tabs = new ArrayList<>();
    tabs.add(new Tab(new HomeFragment(), "首页", R.drawable.tab_icon_homemain_n, R.drawable.tab_icon_homemain_p));
    tabs.add(new Tab(new FollowFragment(), "关注", R.drawable.tab_icon_subcribe_n, R.drawable.tab_icon_subcribe_p));
    tabs.add(new Tab(new ZoneFragment(), "专区", R.drawable.tab_icon_zone_n, R.drawable.tab_icon_zone_p));
    tabs.add(new Tab(new MineFragment(), "我的", R.drawable.tab_icon_mine_n, R.drawable.tab_icon_mine_p));
    ```

* ### STEP3:
    Associate TabAdapter, ViewPager and MagicTab

    ```Java
    TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments, titles, noneIcons, pressedIcons);
    viewPager.setAdapter(tabAdapter);
    magicTab.setViewPager(viewPager);
    magicTab.setMode(MagicTab.MODE_BLANK);
    magicTab.setBlankIndex(2); // Insert blank view in third position of magictab
    ```

## License
Copyright 2016 lybeat

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
