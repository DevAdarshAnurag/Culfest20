package com.nitjsr.culfest20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.PersonRecyclerViewAdapter;
import com.nitjsr.culfest20.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PersonsListActivity extends AppCompatActivity {

    List<Person> Creative, Core, Administrative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Creative = new ArrayList<>();
        Core = new ArrayList<>();
        Administrative = new ArrayList<>();

        Intent intent = getIntent();
        int position = Objects.requireNonNull(intent.getExtras()).getInt("position");

        RecyclerView recyclerView = findViewById(R.id.members_recycler);

        if (position == 0) {

            Creative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Creative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Creative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Creative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Creative);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);

        } else if (position == 1) {
            Core.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Core.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Core.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Core.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Core.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Core.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Core);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        } else {
            Administrative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803",""));

            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Administrative);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }


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
