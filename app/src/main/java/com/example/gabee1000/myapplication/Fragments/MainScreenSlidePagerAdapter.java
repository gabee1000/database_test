package com.example.gabee1000.myapplication.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.example.gabee1000.myapplication.Listeners.OnClickButtonListener;

/**
 * Created by gabee1000 on 2017. 04. 24..
 */

public class MainScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private final int NUM_PAGES = 2;

    public MainScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MainFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
