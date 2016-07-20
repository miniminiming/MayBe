package com.example.administrator.myapplication.activity.coorinatorlayout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class AppBarLayoutActivity extends FragmentActivity implements AppBarLayout.OnOffsetChangedListener {
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private ImageView mProfileImage;
    private int mMaxScrollSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbarlayout);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialup_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.materialup_appbar);
        mProfileImage = (ImageView) findViewById(R.id.materialup_profile_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        appbarLayout.addOnOffsetChangedListener(this);
        //这一步，获得整体的滑动距离
        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    //当这一个方法里为空时，基本功能就基本已经齐全了
    //这个方法是AppBarLayout滑动偏移的回调，仅仅是AppBarLayout的
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        Log.i("AppBarLayoutActivity", "i : " + i);
        //这里做的只是两个动画而已
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;
            mProfileImage.animate().scaleY(0).scaleX(0).setDuration(200).start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    class TabsAdapter extends FragmentPagerAdapter {
        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return MaterialUpConceptFakePage.newInstance();
                case 1:
                    return MaterialUpConceptFakePage.newInstance();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tab 1";
                case 1:
                    return "Tab 2";
            }
            return "";
        }
    }
}
