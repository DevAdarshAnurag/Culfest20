package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.Notification;
import com.nitjsr.culfest20.utilities.EventDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private List<Notification> mList;
    private Context context;


    public NotificationAdapter(Context context, List<Notification> mList) {
        this.context = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.notification_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        holder.title.setText((CharSequence) mList.get(position).getTitle());
        holder.notiIcon.setImageResource(EventDetails.eventIcon[mList.get(position).getEvent()]);
        holder.body.setText((CharSequence) mList.get(position).getBody());

        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expand.setVisibility(View.INVISIBLE);
                holder.body.setVisibility(View.VISIBLE);
                holder.contract.setVisibility(View.VISIBLE);
            }
        });
        holder.contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contract.setVisibility(View.INVISIBLE);
                holder.body.setVisibility(View.GONE);
                holder.expand.setVisibility(View.VISIBLE);
            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.expand.setVisibility(View.INVISIBLE);
                holder.body.setVisibility(View.VISIBLE);
                holder.contract.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.contract.setVisibility(View.INVISIBLE);
        holder.body.setVisibility(View.GONE);
        holder.expand.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title, body;
        private ImageView expand, contract, notiIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expand = itemView.findViewById(R.id.expand_sign_down);
            contract = itemView.findViewById(R.id.contract_sign_up);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.noti_body);
            notiIcon = itemView.findViewById(R.id.noti_icon);
        }

    }
}

