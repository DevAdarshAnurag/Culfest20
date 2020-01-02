package com.nitjsr.culfest20.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.EventsAdapter;
import com.nitjsr.culfest20.models.Events;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private RecyclerView recyclerView;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.gridRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Events> list=new ArrayList<Events>();
        list.add(new Events(R.drawable.ic_mega_events,R.color.colorPrimaryDark,"Mega Events","\u20B9 2,38,000"));
        list.add(new Events(R.drawable.ic_dance,R.color.fb,"Dance","\u20B9 60,600"));
        list.add(new Events(R.drawable.ic_vocals,R.color.insta,"Vocals","\u20B9 58,600"));
        list.add(new Events(R.drawable.ic_qunite,R.color.red,"Quiz","\u20B9 42,000"));
        list.add(new Events(R.drawable.ic_fine_arts,R.color.gold,"Fine Arts","\u20B9 50,800"));
        list.add(new Events(R.drawable.ic_fashion,R.color.colorAccent,"Fashion","\u20B9 70,000"));
        list.add(new Events(R.drawable.ic_dramatics,R.color.colorPrimaryDark,"Dramatics","\u20B9 90,000"));
        list.add(new Events(R.drawable.ic_photography,R.color.fb,"Photography","\u20B9 10,000"));
        list.add(new Events(R.drawable.ic_literary,R.color.insta,"Literary","\u20B9 53,500"));
        list.add(new Events(R.drawable.ic_informals,R.color.red,"Informals"," "));
        EventsAdapter adapter=new EventsAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }
}
