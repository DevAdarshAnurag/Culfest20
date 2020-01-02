package com.nitjsr.culfest20.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private SpinKitView progressBar;
    private RelativeLayout relLayout;

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

        progressBar = view.findViewById(R.id.noti_list_progress_bar);
        relLayout = view.findViewById(R.id.noti_list_view);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        NotificationAdapter recyclerViewAdapter = new NotificationAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        FloatingActionButton buttonReturnToTop = view.findViewById(R.id.fab_noti_top);

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("notification");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                progressBar.setVisibility(View.GONE);
                relLayout.setVisibility(View.VISIBLE);
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Notification notification = ds.getValue(Notification.class);
                    list.add(0,notification);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        buttonReturnToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }
}
