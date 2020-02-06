package com.nitjsr.culfest20.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.Person;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.MyViewHolder> {

    private Context context;

    private List<Person> list;

    public PersonRecyclerViewAdapter(Context context, List<Person> list) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.person_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Person person = list.get(position);
        holder.name.setText(person.getName());
        holder.post.setText(person.getPost());

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("TeamCulfest").child(person.getImgLoc());
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String url = (String) dataSnapshot.getValue();
                    Picasso.get().load(url).placeholder(R.drawable.ic_placeholder_man).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback() {
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

        final String[] number = {person.getWhatsappNumber()};

        if(person.getWhatsappNumber().equals(""))
        {
            holder.whatsapp.setVisibility(View.INVISIBLE);
        }
        if(person.getFacebook().equals(""))
        {
            holder.facebook.setVisibility(View.INVISIBLE);
        }
        if(person.getInstagram().equals(""))
        {
            holder.instagram.setVisibility(View.INVISIBLE);
        }

        holder.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    number[0] = number[0].replace(" ", "").replace("+", "");

                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number[0]) + "@s.whatsapp.net");
                    context.startActivity(sendIntent);

                } catch (Exception e) {
                    Log.e(TAG, "ERROR_OPEN_MESSANGER" + e.toString());
                }
            }
        });

        holder.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(person.getFacebook())));
                } catch (Exception e) {
                    //
                }
            }
        });

        holder.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(person.getInstagram())));
                } catch (Exception e) {
                    //
                }
            }
        });
    }

    private void loadImage(MyViewHolder holder, String url) {
        Picasso.get().load(url).placeholder(R.drawable.ic_placeholder_man).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                loadImage(holder,url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        ImageView whatsapp, instagram, facebook;
        TextView name,post;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            facebook = itemView.findViewById(R.id.person_fb);
            name = itemView.findViewById(R.id.person_name);
            post = itemView.findViewById(R.id.person_post);
            imageView = itemView.findViewById(R.id.person_image);
            whatsapp = itemView.findViewById(R.id.person_whatsapp);
            instagram = itemView.findViewById(R.id.person_insta);
        }
    }
}
