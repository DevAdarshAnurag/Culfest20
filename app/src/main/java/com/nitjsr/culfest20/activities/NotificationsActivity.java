package com.nitjsr.culfest20.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.fragments.NotificationListFragment;
import com.nitjsr.culfest20.fragments.NotificationSettingsFragment;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class NotificationsActivity extends AppCompatActivity {

    int tabNames[] = {R.drawable.ic_noti_notifications, R.drawable.ic_noti_settings};
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tabLayout = findViewById(R.id.noti_tabs);
        viewPager = findViewById(R.id.noti_pager);
        NotiPagerAdapter notiPagerAdapter = new NotiPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(notiPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabNames[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabNames[1]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class NotiPagerAdapter extends FragmentPagerAdapter {

        NotiPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int i) {
            if (i == 0)
                return new NotificationListFragment();
            else if (i == 1)
                return new NotificationSettingsFragment();
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

    }
}
