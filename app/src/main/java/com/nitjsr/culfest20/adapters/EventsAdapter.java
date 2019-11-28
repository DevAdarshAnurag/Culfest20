package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.Events;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.myViewHolder> {
    @NonNull
    private ArrayList<Events> list;
    private Context context;

    public EventsAdapter(Context context,ArrayList<Events>list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0) {
            View view = LayoutInflater.from(context).inflate(R.layout.event_list_item1, parent, false);
            return new myViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.event_list_item2, parent, false);
            return new myViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Events event = list.get(position);
        holder.eventName.setText(event.getEventName());
        holder.prize.setText(event.getEventPrize());
        holder.imageView.setImageResource(event.getEventImage());
        holder.eventLL.setBackground(ContextCompat.getDrawable(context, event.getEventColor()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView eventName, prize;
        LinearLayout eventLL;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.event_image);
            eventName = itemView.findViewById(R.id.event_name);
            prize = itemView.findViewById(R.id.event_prize);
            eventLL = itemView.findViewById(R.id.event_ll);
        }
    }
}
