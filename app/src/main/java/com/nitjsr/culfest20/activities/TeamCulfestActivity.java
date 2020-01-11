package com.nitjsr.culfest20.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.TeamCulfestAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamCulfestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_culfest);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        List<String> list = new ArrayList<>();
        list.add("Administration");
        list.add("Secretaries");
        list.add("Post Holders");
        list.add("Corporate Affairs");
        list.add("Promotion & Social Media");
        list.add("Event Management");
        list.add("Planning & Development");
        list.add("Hospitality");
        list.add("App Development");
        list.add("Web Development");
        list.add("Decoration");
        list.add("Creative");
        list.add("Public Relations");
        list.add("Logistics & Security");

        RecyclerView recyclerView = findViewById(R.id.team_category_recycler);
        TeamCulfestAdapter teamCulfestAdapter = new TeamCulfestAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(teamCulfestAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
