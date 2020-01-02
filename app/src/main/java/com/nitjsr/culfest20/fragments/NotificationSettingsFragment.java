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

    private Boolean isMegaEvents, isDance, isVocals, isQunite, isFineArts, isFashion, isDrama, isPhotography, isLiterary,isInformals;
    private Switch megaEvents,dance, vocals, qunite, fineArts, fashion, drama, photography, literary,informals;
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
        dance = view.findViewById(R.id.dance);
        vocals = view.findViewById(R.id.vocals);
        qunite = view.findViewById(R.id.qunite);
        fineArts = view.findViewById(R.id.fine_arts);
        fashion = view.findViewById(R.id.fashion);
        drama = view.findViewById(R.id.dramatics);
        photography = view.findViewById(R.id.photography);
        literary = view.findViewById(R.id.literary);
        informals = view.findViewById(R.id.informals);
        progressBar = view.findViewById(R.id.noti_progress_bar);
        notiView = view.findViewById(R.id.noti_view);

        OneSignal.getTags(new OneSignal.GetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                Log.d("Notification Debug","Current Tags on User:" + tags.toString());
                try {
                    isMegaEvents = tags.getBoolean("mega_events");
                    isDance = tags.getBoolean("dance");
                    isVocals = tags.getBoolean("vocals");
                    isQunite = tags.getBoolean("quiz");
                    isFineArts = tags.getBoolean("fine_arts");
                    isFashion = tags.getBoolean("fashion");
                    isDrama = tags.getBoolean("dramatics");
                    isPhotography = tags.getBoolean("photography");
                    isLiterary = tags.getBoolean("literary");
                    isInformals = tags.getBoolean("informals");
                    displayTask();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        megaEvents.setOnCheckedChangeListener(this);
        dance.setOnCheckedChangeListener(this);
        vocals.setOnCheckedChangeListener(this);
        qunite.setOnCheckedChangeListener(this);
        fineArts.setOnCheckedChangeListener(this);
        fashion.setOnCheckedChangeListener(this);
        drama.setOnCheckedChangeListener(this);
        photography.setOnCheckedChangeListener(this);
        literary.setOnCheckedChangeListener(this);
        informals.setOnCheckedChangeListener(this);

    }

    private void displayTask() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                notiView.setVisibility(View.VISIBLE);
                megaEvents.setChecked(isMegaEvents);
                dance.setChecked(isDance);
                vocals.setChecked(isVocals);
                qunite.setChecked(isQunite);
                fineArts.setChecked(isFineArts);
                fashion.setChecked(isFashion);
                drama.setChecked(isDrama);
                photography.setChecked(isPhotography);
                literary.setChecked(isLiterary);
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
        if(buttonView.getId() == R.id.dance)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("dance","true");
            else
                OneSignal.sendTag("dance","false");
        }
        if(buttonView.getId() == R.id.vocals)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("vocals","true");
            else
                OneSignal.sendTag("vocals","false");
        }
        if(buttonView.getId() == R.id.qunite)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("quiz","true");
            else
                OneSignal.sendTag("quiz","false");
        }
        if(buttonView.getId() == R.id.fine_arts)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("fine_arts","true");
            else
                OneSignal.sendTag("fine_arts","false");
        }
        if(buttonView.getId() == R.id.fashion)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("fashion","true");
            else
                OneSignal.sendTag("fashion","false");
        }
        if(buttonView.getId() == R.id.dramatics)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("dramatics","true");
            else
                OneSignal.sendTag("dramatics","false");
        }
        if(buttonView.getId() == R.id.photography)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("photography","true");
            else
                OneSignal.sendTag("photography","false");
        }
        if(buttonView.getId() == R.id.literary)
        {
            if(megaEvents.isChecked())
                OneSignal.sendTag("literary","true");
            else
                OneSignal.sendTag("literary","false");
        }
    }
}
