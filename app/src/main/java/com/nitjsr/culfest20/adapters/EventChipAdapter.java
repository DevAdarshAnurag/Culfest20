package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.EventActivity;
import com.nitjsr.culfest20.models.Events;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventChipAdapter extends RecyclerView.Adapter<EventChipAdapter.myViewHolder> {

    private ArrayList<Events> list;
    private Context context;

    public EventChipAdapter(Context context, ArrayList<Events> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventChipAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_chip_list_item, parent, false);
        return new EventChipAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventChipAdapter.myViewHolder holder, int position) {
        Events event = list.get(position);
        holder.chip.setText(event.getEventName());
        holder.chip.setChipIconResource(event.getEventImage());
        holder.chip.setChipIconSize(32);
        holder.chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("event_id",position);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
        });
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(event.getEventName()).child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    holder.chip.setChipBackgroundColorResource(event.getEventColor());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        Chip chip;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.event_chip_name);
        }
    }
}
