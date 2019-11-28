package com.nitjsr.culfest20.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.github.ybq.android.spinkit.SpinKitView;
import com.nitjsr.culfest20.R;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSettingsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private Boolean isMegaEvents, isInformals;
    private Switch megaEvents, informals;
    private SpinKitView progressBar;
    private RelativeLayout notiView;

    public NotificationSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        megaEvents = view.findViewById(R.id.mega_events);
        informals = view.findViewById(R.id.informals);
        progressBar = view.findViewById(R.id.noti_progress_bar);
        notiView = view.findViewById(R.id.noti_view);

        OneSignal.getTags(new OneSignal.GetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                Log.d("Notification Debug","Current Tags on User:" + tags.toString());
                try {
                    isMegaEvents = tags.getBoolean("mega_events");
                    isInformals = tags.getBoolean("informals");
                    displayTask();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        megaEvents.setOnCheckedChangeListener(this);
        informals.setOnCheckedChangeListener(this);

    }

    private void displayTask() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                notiView.setVisibility(View.VISIBLE);
                megaEvents.setChecked(isMegaEvents);
                informals.setChecked(isInformals);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.mega_events)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("mega_events","true");
            else
                OneSignal.sendTag("mega_events","false");
        }
        if(buttonView.getId() == R.id.informals)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("informals","true");
            else
                OneSignal.sendTag("informals","false");
        }
    }
}
