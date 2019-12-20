package com.nitjsr.culfest20.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.SubEventAdapter;
import com.nitjsr.culfest20.models.SubEvents;
import com.nitjsr.culfest20.utilities.EventDetails;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        int x = getIntent().getIntExtra("event_id", 0);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(EventDetails.eventName[x]);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RelativeLayout scrollRL = findViewById(R.id.scroll_rl);

        BottomSheetBehavior behavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet));
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED)
                    scrollRL.setVisibility(View.GONE);
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                scrollRL.setScaleX(1 - slideOffset);
                scrollRL.setScaleY(1 - slideOffset);
                if(slideOffset < 0.2)
                    scrollRL.setVisibility(View.VISIBLE);
            }
        });

        ArrayList<SubEvents> subEvents = new ArrayList<>();
        for (int i = 0; i < EventDetails.subEventList[x].length; i++) {
            SubEvents subEvent = new SubEvents(EventDetails.subEventList[x][i], EventDetails.description[x][i],
                    EventDetails.subEventPrizes[x][i], EventDetails.rules[x][i], EventDetails.coordinators[x][i]);
            subEvents.add(subEvent);
        }
        RecyclerView subEventRV = findViewById(R.id.sub_events_rv);
        subEventRV.setLayoutManager(new LinearLayoutManager(this));
        subEventRV.setAdapter(new SubEventAdapter(this, subEvents));
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

}
