package com.nitjsr.culfest20.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ScrollView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.PersonRecyclerViewAdapter;
import com.nitjsr.culfest20.models.Person;
import com.nitjsr.culfest20.models.Post;
import com.nitjsr.culfest20.utilities.EventDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PersonsListActivity extends AppCompatActivity {

    List<Person> Creative, Core, Administrative,Secretaries, Post_Holders,Corporate_affairs, Promotion, Event_Management, Planning_and_development, Hospitality, AppDevelopment, WebDevelopment, Decoration, Public_relation, Logistics ;

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
        Secretaries = new ArrayList<>();
        Post_Holders = new ArrayList<>();
        Corporate_affairs = new ArrayList<>();
        Promotion = new ArrayList<>();
        Event_Management = new ArrayList<>();
        Planning_and_development = new ArrayList<>();
        Hospitality = new ArrayList<>();
        AppDevelopment = new ArrayList<>();
        WebDevelopment = new ArrayList<>();
        Decoration = new ArrayList<>();
        Public_relation = new ArrayList<>();
        Logistics = new ArrayList<>();

        Intent intent = getIntent();
        int position = Objects.requireNonNull(intent.getExtras()).getInt("position");

        RecyclerView recyclerView = findViewById(R.id.members_recycler);

        if (position == 0) {
            Administrative.add(new Person(R.drawable.ic_launcher, "Prof. Karunesh Kumar Shukla", "Director, NIT Jamshedpur","","",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Prof. Sanjay", "Dean Student Welfare","","",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Dr. Amit Prakash", "Professor-in-Charge","","",""));
            Administrative.add(new Person(R.drawable.ic_launcher, "Dr. Keshav Kumar Sharma", "Professor-in-Charge","","",""));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Administrative);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);

        } else if (position == 1) {
            Secretaries.add(new Person(R.drawable.ic_launcher, "Kshitij", "Joint Cultural Secretary","+918210046575","","https://www.instagram.com/roykshitij/"));
            Secretaries.add(new Person(R.drawable.ic_launcher, "Harshit Saini", "Executive Secretary","+919471501123","","https://www.instagram.com/hundred.pack.abs/"));
            Secretaries.add(new Person(R.drawable.ic_launcher, "Nikita Smriti Lugun", "Joint Cultural Secretary","+918797996758","","https://www.instagram.com/lil_missnikita/"));
            Secretaries.add(new Person(R.drawable.ic_launcher, "Shristi Sinha", "Secretary External Affairs","+917209727245","","https://www.instagram.com/shristi_niranjan_sinha/"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Secretaries);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        } else if(position==2)
        {
            Post_Holders.add(new Person(R.drawable.ic_launcher, "Kanishka ", "Spokesperson","+917277181420","","https://www.instagram.com/its__scherbatsky/"));
            Post_Holders.add(new Person(R.drawable.ic_launcher, "Shalini", "Marketing Head","+917870328685","","https://www.instagram.com/_shalinisinha_"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Post_Holders);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if(position==3)
        {
            Corporate_affairs.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            Corporate_affairs.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            Corporate_affairs.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            Corporate_affairs.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Corporate_affairs);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==4)
        {
            Promotion.add(new Person(R.drawable.ic_launcher, "Saziya Ahasan ", "Promotion and Social Meadia","+919128868663","","http://instagram.com/ahsan_sazy/"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Promotion);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==5)
        {
            Event_Management.add(new Person(R.drawable.ic_launcher, "Rashmi Rathee", "Event Management","+919507087258","","http://Instagram.com/asthanasuraj2911"));
            Event_Management.add(new Person(R.drawable.ic_launcher, "Ankita Kumari", "Event Management","+917635062185","","https://www.instagram.com/cutie_pie.21/"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Event_Management);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==6)
        {
            Planning_and_development.add(new Person(R.drawable.ic_launcher, "Anmol", "P & D Head","+919801119003","","https://www.instagram.com/_anmol__gupta_/"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Planning_and_development);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==7)
        {
            Hospitality.add(new Person(R.drawable.ic_launcher, "Ashish Kumar Raj", "Hospitality","+917209408160","","https://www.instagram.com/"));
            Hospitality.add(new Person(R.drawable.ic_launcher, "Saumya Singh ", "Hospitality","+917739135255","https://www.facebook.com/profile.php?id=100012669512607","https://instagram.com/__saumya_singh_"));
            Hospitality.add(new Person(R.drawable.ic_launcher, "Uppu Goutami", "Hospitality","+917059603339","","https://www.instagram.com/_____.goutami._____/ "));
            Hospitality.add(new Person(R.drawable.ic_launcher, "Priya Bharti", "Hospitality","+917294958587","","https://www.instagram.com/priya_barnwal02/"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this,Hospitality);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==8)
        {
            AppDevelopment.add(new Person(R.drawable.ic_launcher, "Adarsh Anurag", "App Head","+919801540653","https://www.facebook.com/a4adarshanurag","https://www.instagram.com/adarsh_nightmare_anurag"));
            AppDevelopment.add(new Person(R.drawable.ic_launcher, "Himesh Patel", "App Head","+918619313065","","https://www.instagram.com/i_am_nucking_futzzzzz"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, AppDevelopment);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==9)
        {
            WebDevelopment.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            WebDevelopment.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, WebDevelopment);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==10)
        {
            Decoration.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            Decoration.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            Decoration.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            Decoration.add(new Person(R.drawable.ic_launcher, "Kartik", "Head","+918174033803","",""));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Decoration);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==11)
        {
            Creative.add(new Person(R.drawable.ic_launcher, "Kr. Shubham Anand", "Creative Head","+919430789829","",""));
            Creative.add(new Person(R.drawable.ic_launcher, "Yuktima Chaurasiya ", "Creative Head","+916205572438","","https://www.instagram.com/yuktimachaurasiya"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Creative);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==12)
        {
            Public_relation.add(new Person(R.drawable.ic_launcher, "Pragati Gupta", "Public Relation","+9188235817548","","Instagram.com/___p0g0___"));
            Public_relation.add(new Person(R.drawable.ic_launcher, "Shailja", "Public Relation","+919470287800","","https://www.instagram.com/_shall_jaa_/"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Public_relation);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        else if (position==13)
        {
            Logistics.add(new Person(R.drawable.ic_launcher, "Jitendra Kumar", "Logistics & Security","+917739748903","","https://www.instagram.com/_jitendra_1546/"));
            Logistics.add(new Person(R.drawable.ic_launcher, "Naresh Bauri", "Logistics & Security","+917992303591","","https://www.instagram.com/naresh_v4/"));
            Logistics.add(new Person(R.drawable.ic_launcher, "Shubham Raj", "Logistics & Security","+918409111475","","https://www.instagram.com/shubhamyenohraj?r=nametag"));
            PersonRecyclerViewAdapter recyclerViewAdapter = new PersonRecyclerViewAdapter(this, Logistics);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(recyclerViewAdapter);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
