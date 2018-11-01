package com.example.shauryatrivedi.metoo.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shauryatrivedi.metoo.Fragments.Laws;
import com.example.shauryatrivedi.metoo.Fragments.MeToo;
import com.example.shauryatrivedi.metoo.Fragments.MeTooIn;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public ViewPagerAdapter(FragmentManager fm, int mNoOfTabs) {
        super(fm);
        this.mNoOfTabs = mNoOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Laws lawTab = new Laws();
                    return lawTab;
                case 1:
                    MeToo meTooTab = new MeToo();
                    return meTooTab;
                case 2:
                    MeTooIn MeTooInTab = new MeTooIn();
                    return MeTooInTab;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
