package com.lybeat.magictab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Author: lybeat
 * Date: 2016/7/22
 */
public class MagicTab extends FrameLayout {

    private static final String TAG = "PagerTabStrip";

    public static final int MODE_NORMAL = 0;
    public static final int MODE_BLANK = 1;

    private LinearLayout tabsContainer;
    private ViewPager viewPager;

    private int tabCount;
    private int currentPosition;
    private int mode;
    private int blankIndex;

    private Paint rectPaint;

    private int tabPadding = 4; // dp
    private int tabTextSize = 12; // sp
    private int tabTextColorNormal = 0xff666666;
    private int tabTextColorPressed = 0xffff5959;

    public MagicTab(Context context) {
        this(context, null);
    }

    public MagicTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        tabTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabTextSize, dm);
        tabPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PagerTabStrip);
        tabPadding = a.getDimensionPixelSize(R.styleable.PagerTabStrip_ptsPadding, tabPadding);
        tabTextSize = a.getDimensionPixelSize(R.styleable.PagerTabStrip_ptsTextSize, tabTextSize);
        tabTextColorNormal = a.getColor(R.styleable.PagerTabStrip_ptsTextColorNormal, tabTextColorNormal);
        tabTextColorPressed = a.getColor(R.styleable.PagerTabStrip_ptsTextColorPressed, tabTextColorPressed);
        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Paint.Style.FILL);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }

        viewPager.addOnPageChangeListener(new PagerListener());

        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        if (viewPager == null) {
            return;
        }

        tabsContainer.removeAllViews();
        tabCount = viewPager.getAdapter().getCount();
        if (mode == MODE_NORMAL) {
            addTabsNormal();
        } else {
            addTabsBlank();
        }
    }

    private void addTabsNormal() {
        for (int i = 0; i < tabCount; i++) {
            if (i == currentPosition) {
                addTab(i, (String) viewPager.getAdapter().getPageTitle(i), tabTextColorPressed,
                        ((TabIcon) viewPager.getAdapter()).getPagePressedIconId(i));
            } else {
                addTab(i, (String) viewPager.getAdapter().getPageTitle(i), tabTextColorNormal,
                        ((TabIcon) viewPager.getAdapter()).getPageNormalIconId(i));
            }
        }
    }

    private void addTabsBlank() {
        for (int i = 0; i < tabCount + 1; i++) {
            if (i == blankIndex) {
                addBlankTab(blankIndex);
            } else if (i < blankIndex) {
                if (i == currentPosition) {
                    addTab(i, (String) viewPager.getAdapter().getPageTitle(i), tabTextColorPressed,
                            ((TabIcon) viewPager.getAdapter()).getPagePressedIconId(i));
                } else {
                    addTab(i, (String) viewPager.getAdapter().getPageTitle(i), tabTextColorNormal,
                            ((TabIcon) viewPager.getAdapter()).getPageNormalIconId(i));
                }
            } else {
                if (i == currentPosition + 1) {
                    addTab(i, (String) viewPager.getAdapter().getPageTitle(i - 1), tabTextColorPressed,
                            ((TabIcon) viewPager.getAdapter()).getPagePressedIconId(i - 1));
                } else {
                    addTab(i, (String) viewPager.getAdapter().getPageTitle(i - 1), tabTextColorNormal,
                            ((TabIcon) viewPager.getAdapter()).getPageNormalIconId(i - 1));
                }
            }
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
        notifyDataSetChanged();
    }

    public void setBlankIndex(int index) {
        if (viewPager == null) {
            return;
        }

        if (index < 0 || index >= viewPager.getAdapter().getCount()) {
            blankIndex = viewPager.getAdapter().getCount() / 2;
        } else {
            this.blankIndex = index;
        }
        notifyDataSetChanged();
    }

    private void addTab(final int position, String title, int titleColor, int resId) {
        ImageView tabImg = new ImageView(getContext());
        tabImg.setImageResource(resId);

        TextView tabTxt = new TextView(getContext());
        tabTxt.setSingleLine();
        tabTxt.setText(title);
        tabTxt.setTextColor(titleColor);
        tabTxt.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSize);
        tabTxt.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        LinearLayout tab = new LinearLayout(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        tab.setOrientation(LinearLayout.VERTICAL);
        tab.setLayoutParams(lp);
        tab.setPadding(0, tabPadding, 0, tabPadding);
        tab.setGravity(Gravity.CENTER_HORIZONTAL);
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mode == MODE_NORMAL) {
                    viewPager.setCurrentItem(position, false);
                } else {
                    if (position < blankIndex) {
                        viewPager.setCurrentItem(position, false);
                    } else {
                        viewPager.setCurrentItem(position - 1, false);
                    }
                }
            }
        });
        tab.addView(tabImg);
        tab.addView(tabTxt);

        tabsContainer.addView(tab, position, lp);
    }

    private void addBlankTab(int position) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        LinearLayout tab = new LinearLayout(getContext());
        tab.setOrientation(LinearLayout.VERTICAL);
        tab.setLayoutParams(lp);
        tab.setPadding(0, tabPadding, 0, tabPadding);
        tabsContainer.addView(tab, position, lp);
    }

    private class PagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            notifyDataSetChanged();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
