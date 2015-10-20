package com.utupio.zongheng.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.utupio.zongheng.fragment.CompetitionScheduledFragment;
import com.utupio.zongheng.fragment.CircleInfoFragment;
import com.utupio.zongheng.fragment.CompetitionNewsFragment;
import com.utupio.zongheng.fragment.ShoppingInfoFragment;

/**
 * Created by Zhang Yan on 2015/8/24.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int nNumOfTabs;
    public PagerAdapter(FragmentManager fm, int nNumOfTabs) {
        super(fm);
        this.nNumOfTabs = nNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CompetitionScheduledFragment competitionScheduledFragment = new CompetitionScheduledFragment();
                return competitionScheduledFragment;
            case 1:
                CircleInfoFragment circleInfoFragment = new CircleInfoFragment();
                return circleInfoFragment;
            case 2:
                CompetitionNewsFragment competitionNewsFragment = new CompetitionNewsFragment();
                return competitionNewsFragment;
            case 3:
                ShoppingInfoFragment shoppingInfoFragment = new ShoppingInfoFragment();
                return shoppingInfoFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}

