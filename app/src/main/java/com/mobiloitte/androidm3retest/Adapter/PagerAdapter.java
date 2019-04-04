package com.mobiloitte.androidm3retest.Adapter;



import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mobiloitte.androidm3retest.Fragments.CallFragment;
import com.mobiloitte.androidm3retest.Fragments.MissCallFragment;


    public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: return new CallFragment();
            case 1: return new MissCallFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Call History";
            case 1: return "Missed Call";
            default: return null;
        }
    }
}
