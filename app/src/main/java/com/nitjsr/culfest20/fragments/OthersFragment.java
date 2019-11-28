package com.nitjsr.culfest20.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.DeveloperActivity;
import com.nitjsr.culfest20.activities.MapsActivity;
import com.nitjsr.culfest20.utilities.BounceView;
import com.nitjsr.culfest20.whatsapp.EntryActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OthersFragment extends Fragment {

    private LinearLayout sponsors, team, map, stickers, developers, share;

    public OthersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_others, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sponsors = view.findViewById(R.id.cv_sponsors);
        team = view.findViewById(R.id.cv_team);
        stickers = view.findViewById(R.id.cv_stickers);
        map = view.findViewById(R.id.cv_maps);
        developers = view.findViewById(R.id.cv_developers);
        share = view.findViewById(R.id.cv_share);

        stickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EntryActivity.class);
                launch(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                launch(intent);
            }
        });
        developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeveloperActivity.class);
                launch(intent);
            }
        });

        BounceView.addAnimTo(sponsors);
        BounceView.addAnimTo(team);
        BounceView.addAnimTo(stickers);
        BounceView.addAnimTo(map);
        BounceView.addAnimTo(developers);
        BounceView.addAnimTo(share);
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
}
