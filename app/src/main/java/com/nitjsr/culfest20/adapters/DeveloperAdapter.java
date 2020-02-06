package com.nitjsr.culfest20.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.Developer;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.MyViewHolder> {
    private ArrayList<Developer> devs;
    private Context context;

    public DeveloperAdapter(ArrayList<Developer> devs, Context context) {
        this.devs = devs;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.developer_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.devname.setText(devs.get(position).getName());
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Developers").child(devs.get(position).getImgLoc());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String url = (String) dataSnapshot.getValue();
                    Picasso.get().load(url).placeholder(R.drawable.ic_placeholder_man).networkPolicy(NetworkPolicy.OFFLINE).into(holder.devimg, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            loadImage(holder, url);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        holder.insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(devs.get(position).getInsta()));
                context.startActivity(intent);
            }
        });

        holder.linkd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(devs.get(position).getLinkedIn()));
                context.startActivity(intent);
            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + devs.get(position).getPhone()));
                context.startActivity(intent);
            }
        });

    }

    private void loadImage(DeveloperAdapter.MyViewHolder holder, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_placeholder_man).into(holder.devimg, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                loadImage(holder, url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return devs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView devimg;
        TextView devname;
        ImageView insta, linkd, call;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            devimg = itemView.findViewById(R.id.dev_image);
            devname = itemView.findViewById(R.id.dev_name);
            insta = itemView.findViewById(R.id.dev_insta);
            call = itemView.findViewById(R.id.dev_call);
            linkd = itemView.findViewById(R.id.dev_linked_in);
        }
    }
}
