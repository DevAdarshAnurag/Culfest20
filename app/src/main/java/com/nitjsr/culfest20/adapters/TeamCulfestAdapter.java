package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.PersonsListActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TeamCulfestAdapter extends RecyclerView.Adapter<TeamCulfestAdapter.MyViewHolder> {

    private Context context;
    private List list;

    public TeamCulfestAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.team_culfest_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.team_category.setText(list.get(position).toString());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = Integer.toString(position);
                //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PersonsListActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView team_category;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            team_category = itemView.findViewById(R.id.team_category);
            cardView = itemView.findViewById(R.id.card_view_team);
        }
    }
}
