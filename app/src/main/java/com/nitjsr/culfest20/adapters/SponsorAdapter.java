package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nitjsr.culfest20.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.myViewHolder> {

    ArrayList<String> sponsorList;
    Context context;

    public SponsorAdapter(ArrayList<String> sponsorList, Context context) {
        this.sponsorList = sponsorList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sponsor_list_item, parent, false);
        return new SponsorAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String url = sponsorList.get(position);
        Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).into(holder.sponsorImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.relLoader.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                loadImage(holder, url);
            }
        });
    }

    private void loadImage(myViewHolder holder, String url) {
        Picasso.get().load(url).into(holder.sponsorImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.relLoader.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                loadImage(holder, url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sponsorList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView sponsorImage;
        RelativeLayout relLoader;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            sponsorImage = itemView.findViewById(R.id.sponsors_image_view);
            relLoader = itemView.findViewById(R.id.sponsor_image_loader);
        }
    }
}
