package com.nitjsr.culfest20.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.NotificationAdapter;
import com.nitjsr.culfest20.models.Notification;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationListFragment extends Fragment {

    private List<Notification> list = new ArrayList<>();

    public NotificationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        list.add(new Notification("Hello 0. This is very very big Title.", "Description 1. \n Big description. \n big"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description is big. Bahut bada description. Bahute bada description."));
        list.add(new Notification("Hello 1", "Description 1"));
        list.add(new Notification("Hello 1", "Description 1"));
        list.add(new Notification("Hello 1", "Description 1"));
        list.add(new Notification("Hello 1", "Description 1"));

        list.add(new Notification("Hello 1", "Description 1"));
        list.add(new Notification("Hello 1", "Description 1"));
        list.add(new Notification("Hello 1", "Description 1"));
        list.add(new Notification("Hello 1", "Description 1"));


        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        NotificationAdapter recyclerViewAdapter = new NotificationAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        final FloatingActionButton buttonReturnToTop = view.findViewById(R.id.fab_noti_top);

        buttonReturnToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
}
