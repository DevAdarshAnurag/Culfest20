package com.nitjsr.culfest20.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.DeveloperActivity;
import com.nitjsr.culfest20.activities.ScheduleActivity;
import com.nitjsr.culfest20.utilities.BounceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private ImageView day1,day2,day3;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        day1 = view.findViewById(R.id.day1);
        day2 = view.findViewById(R.id.day2);
        day3= view.findViewById(R.id.day3);

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra("day",1);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                launch(intent);
            }
        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra("day",2);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                launch(intent);
            }
        });

        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra("day",3);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                launch(intent);
            }
        });

        BounceView.addAnimTo(day1);
        BounceView.addAnimTo(day2);
        BounceView.addAnimTo(day3);
    }

    private void launch(Intent i)
    {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(i);
            }

        }, 100);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
