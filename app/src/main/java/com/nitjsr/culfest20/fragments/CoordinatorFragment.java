package com.nitjsr.culfest20.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.SubEventActivity;

import androidx.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoordinatorFragment extends Fragment {


    public CoordinatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinator, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView body = view.findViewById(R.id.body);
        body.setText(SubEventActivity.coordinators);
    }
}
