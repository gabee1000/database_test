package com.example.gabee1000.myapplication;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import com.example.gabee1000.myapplication.Animations.ZoomOutPageTransformer;
import com.example.gabee1000.myapplication.Fragments.MainScreenSlidePagerAdapter;
import com.example.gabee1000.myapplication.Listeners.OnClickButtonListener;
import com.example.gabee1000.myapplication.Listeners.ScreenSliderPagerListener;

/**
 * Created by gabee1000 on 2017. 04. 24..
 */

public class MainContainerActivity extends AppCompatActivity implements OnClickButtonListener {
    public static final String SAVE_BUTTON = "save_button";
    public static final String REGISTER_BUTTON = "register_button";
    private ViewPager mMainScreenSlidePager;
    private MainScreenSlidePagerAdapter mMainPagerAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_screen_slide);
        init();
        action();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mMainScreenSlidePager = (ViewPager) findViewById(R.id.main_container);
        mMainScreenSlidePager.setPageMargin(60);
        mMainPagerAdapter = new MainScreenSlidePagerAdapter(getSupportFragmentManager());
        mMainScreenSlidePager.setAdapter(mMainPagerAdapter);
        mMainScreenSlidePager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void action() {
        mMainScreenSlidePager.addOnPageChangeListener(new ScreenSliderPagerListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                switch (mMainScreenSlidePager.getCurrentItem()) {
                    case 0:
                        toolbar.setTitle(R.string.app_name);
                        break;
                    case 1:
                        toolbar.setTitle(R.string.register);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mMainScreenSlidePager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mMainScreenSlidePager.setCurrentItem(mMainScreenSlidePager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onButtonClicked(String buttonName) {
        switch (buttonName) {
            case SAVE_BUTTON:
                mMainScreenSlidePager.setCurrentItem(0);
                break;
            case REGISTER_BUTTON:
                mMainScreenSlidePager.setCurrentItem(1);
                break;
            default:
                mMainScreenSlidePager.setCurrentItem(0);
                break;
        }
    }
}
