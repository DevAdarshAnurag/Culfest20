package com.nitjsr.culfest20.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.fragments.AboutFragment;
import com.nitjsr.culfest20.fragments.CoordinatorFragment;
import com.nitjsr.culfest20.fragments.RulesFragment;
import com.nitjsr.culfest20.utilities.FragmentTransactionExtended;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SubEventActivity extends AppCompatActivity implements View.OnClickListener {

    public static String description, rules, coordinators;
    ImageView about, details, coordinator;
    View aboutView, detailsView, coordinatorView;
    FrameLayout parent;
    LinearLayout sideNav;
    AboutFragment aboutFragment;
    RulesFragment rulesFragment;
    CoordinatorFragment coordinatorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        String title = getIntent().getStringExtra("sub_event");
        description = getIntent().getStringExtra("description");
        rules = getIntent().getStringExtra("rules");
        coordinators = getIntent().getStringExtra("coordinators");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        about = findViewById(R.id.but_about);
        details = findViewById(R.id.but_details);
        coordinator = findViewById(R.id.but_coordinator);
        sideNav = findViewById(R.id.side_nav);
        parent = findViewById(R.id.parent);
        aboutView = findViewById(R.id.about_view);
        detailsView = findViewById(R.id.details_view);
        coordinatorView = findViewById(R.id.coordinator_view);

        about.setOnClickListener(this);
        details.setOnClickListener(this);
        coordinator.setOnClickListener(this);

        aboutFragment = new AboutFragment();
        rulesFragment = new RulesFragment();
        coordinatorFragment = new CoordinatorFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.content, aboutFragment).commit();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_about:
                sideNav.setBackgroundResource(R.drawable.bg_sub_event_about);
                aboutView.setVisibility(View.VISIBLE);
                detailsView.setVisibility(View.INVISIBLE);
                coordinatorView.setVisibility(View.INVISIBLE);
                setFragment(aboutFragment);
                break;
            case R.id.but_details:
                sideNav.setBackgroundResource(R.drawable.bg_sub_event_details);
                aboutView.setVisibility(View.INVISIBLE);
                detailsView.setVisibility(View.VISIBLE);
                coordinatorView.setVisibility(View.INVISIBLE);
                setFragment(rulesFragment);
                break;
            case R.id.but_coordinator:
                sideNav.setBackgroundResource(R.drawable.bg_sub_event_coordinator);
                aboutView.setVisibility(View.INVISIBLE);
                detailsView.setVisibility(View.INVISIBLE);
                coordinatorView.setVisibility(View.VISIBLE);
                setFragment(coordinatorFragment);
                break;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransactionExtended fragmentTransactionExtended =
                new FragmentTransactionExtended(this, fragmentTransaction, new AboutFragment(), fragment, R.id.content);
        fragmentTransactionExtended.addTransition(FragmentTransactionExtended.GLIDE);
        fragmentTransactionExtended.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
