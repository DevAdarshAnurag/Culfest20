package com.nitjsr.culfest20.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.SubEventActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RulesFragment extends Fragment {


    public RulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView body = view.findViewById(R.id.body);
        body.setText(SubEventActivity.rules);
    }

}
