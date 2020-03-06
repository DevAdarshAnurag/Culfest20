package com.nitjsr.culfest20.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.FAQAdapter;
import com.nitjsr.culfest20.models.FAQ;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FAQActivity extends AppCompatActivity {

    private List<FAQ> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        list.add(new FAQ("Question : " + "How one can be the Campus Ambassador of Culfest 20?", "Answer : " + "If you want to represent Culfest in your college and city, it is very simple through Campus Ambassador Program. Be a part of it and help us bring more participation. Anyone with good communication and managerial skills can apply for this. For more details on registration you may contact Public Relations team of Culfest 20."));

        list.add(new FAQ("Question : " + "Can people of any age-group attend Culfest 20?", "Answer : " + "No, Only school or college students are allowed."));

        list.add(new FAQ("Question : " + "How do I register online for the  Culfest 20?", "Answer : " + "Just sign into Culfest'20 app, fill your details to register and then go to your Profile section in the app and get your Culfest ID(CFAXXXX). But offline registration needs to be done in order to participate in the events."));

        list.add(new FAQ("Question : " + "What is the process of offline registration?", "Answer : " + "Registration process will start on 10th February from 7:00 AM onwards for students not from NIT Jamshedpur and will be open all the time during the fest except during the time of pronites. You need to show your Culfest ID and pay the required amount in order to confirm your registration. The registering participants also need to install the CureMantra App on their device."));

        list.add(new FAQ("Question : " + "What is the registration fee for Culfest 20?", "Answer : " + "Registration fee for Culfest'20 is \u20B9600 for students not from NIT Jamshedpur."));

        list.add(new FAQ("Question : " + "I cannot see my Culfest id, what to do?","Answer : " + "Your Culfest id can be found in the profile section. In case you did not get a Culfest id or cannot see your Culfest id please contact app development team."));

        list.add(new FAQ("Question : " + "How can I attend the concerts?", "Answer : " + "NIT Jamshedpur students need to show their college id cards at the entry and for outsiders, registration ticket is compulsory."));

        list.add(new FAQ("Question : " + "What type of accommodation will be provided?", "Answer : " + "Accommodation will be provided to boys and girls in well secured separate residential complexes on the campus of NIT Jamshedpur."));

        list.add(new FAQ("Question : " + "Does accommodation fee include the food facility as well?", "Answer : " + "No. The accommodation charges don’t include food. You need to pay separately for mess coupons. However, there will be food courts operational during Culfest to cater to the food requirements."));

        list.add(new FAQ("Question : " + "Who can we contact in case of need of help/emergency?", "Answer : " + "You can contact coordinators of events or any core team members."));

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FAQAdapter recyclerViewAdapter = new FAQAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        final FloatingActionButton buttonReturnToTop = findViewById(R.id.fab_faq_top);

        buttonReturnToTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
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
