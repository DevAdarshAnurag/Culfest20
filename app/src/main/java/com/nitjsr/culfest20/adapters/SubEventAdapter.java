package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.SubEvents;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubEventAdapter extends RecyclerView.Adapter<SubEventAdapter.myViewHolder> {

    private Context context;
    private ArrayList<SubEvents> subEvents;

    public SubEventAdapter(Context context, ArrayList<SubEvents> subEvents) {
        this.context = context;
        this.subEvents = subEvents;
    }

    @NonNull
    @Override
    public SubEventAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_event_list_item, parent, false);
        return new SubEventAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubEventAdapter.myViewHolder holder, int position) {
        SubEvents subEvent = subEvents.get(position);
        holder.subEventName.setText(subEvent.getName());
    }

    @Override
    public int getItemCount() {
        return subEvents.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView subEventName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            subEventName = itemView.findViewById(R.id.sub_event_name);
        }
    }
}
