package com.nitjsr.culfest20.adapters;


import com.nitjsr.culfest20.fragments.AboutUsFragment;
import com.nitjsr.culfest20.fragments.EventsFragment;
import com.nitjsr.culfest20.fragments.OthersFragment;
import com.nitjsr.culfest20.fragments.PostFragment;
import com.nitjsr.culfest20.fragments.ScheduleFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i == 0)
            return new AboutUsFragment();
        else if (i == 1)
            return new EventsFragment();
        else if (i == 2)
            return new PostFragment();
        else if(i == 3)
            return new ScheduleFragment();
        else
            return new OthersFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
