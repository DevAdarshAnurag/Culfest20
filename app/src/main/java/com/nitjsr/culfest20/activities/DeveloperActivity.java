package com.nitjsr.culfest20.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.DeveloperAdapter;
import com.nitjsr.culfest20.models.Developer;

import java.util.ArrayList;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<Developer> devList = new ArrayList<Developer>();
        devList.add(new Developer("Adarsh 'nightmare' Anurag","8797834204", "https://www.linkedin.com/in/adarsh-anurag-b7a0ba128/","https://www.instagram.com/adarsh_nightmare_anurag", R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Champa 'petrol' Sama","","","",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Abhinay Kumar", "8825326346",  "https://www.linkedin.com/in/abhinay-kumar-3b3345178","https://www.instagram.com/kumarabhinay599",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Nikhil","","","",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Shubham","","","",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Martande","","","",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Dhambere","","","",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Martande","","","",R.drawable.ic_stat_onesignal_default));
        devList.add(new Developer("Dhambere","","","",R.drawable.ic_stat_onesignal_default));

        RecyclerView recyclerView = findViewById(R.id.rv_developers);
        DeveloperAdapter developerAdapter = new DeveloperAdapter(devList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(DeveloperActivity.this));
        recyclerView.setAdapter(developerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}