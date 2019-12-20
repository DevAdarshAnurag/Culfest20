package com.nitjsr.culfest20.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nitjsr.culfest20.R;
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
                Toast.makeText(getActivity(),"Coming Soon...",Toast.LENGTH_SHORT).show();
            }
        });

        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Coming Soon...",Toast.LENGTH_SHORT).show();
            }
        });

        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Coming Soon...",Toast.LENGTH_SHORT).show();
            }
        });

        BounceView.addAnimTo(day1);
        BounceView.addAnimTo(day2);
        BounceView.addAnimTo(day3);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
