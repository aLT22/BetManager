package com.bytebuilding.betmanager.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bytebuilding.betmanager.tabs.ActiveBetsFragment;
import com.bytebuilding.betmanager.tabs.AddBetFragment;
import com.bytebuilding.betmanager.tabs.InactiveBetsFragment;
import com.bytebuilding.betmanager.tabs.MoneysFragment;

/**
 * Created by Alexey on 11.10.2016.
 */

public class BetTabAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public BetTabAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);

        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoneysFragment();
            case 1:
                return new InactiveBetsFragment();
            case 2:
                return new ActiveBetsFragment();
            case 3:
                return new AddBetFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
