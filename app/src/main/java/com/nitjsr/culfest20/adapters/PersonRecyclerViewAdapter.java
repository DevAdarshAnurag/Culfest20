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

import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.Person;
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

        holder.name.setText(list.get(position).getName());
        holder.post.setText(list.get(position).getPost());

        Picasso.get().load(list.get(position).getThumbnail()).placeholder(R.drawable.ic_launcher).into(holder.imageView);
        final String[] number = {list.get(position).getWhatsappNumber()};


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

//        holder.facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + fb_id));
//                    context.startActivity(intent);
//                } catch (Exception e) {
//                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/" + fb_username)));
//                }
//            }
//        });

//        holder.linkedin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/in/" + linkedId));
//                final PackageManager packageManager = context.getPackageManager();
//                final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//                if (list.isEmpty()) {
//                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + linkedId));
//                }
//                context.startActivity(intent);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        ImageView whatsapp, instagram, linkedin;
        TextView name,post;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linkedin = itemView.findViewById(R.id.linkedin);
            name = itemView.findViewById(R.id.person_name);
            post = itemView.findViewById(R.id.person_post);
            imageView = itemView.findViewById(R.id.person_image);
            whatsapp = itemView.findViewById(R.id.person_whatsapp);
            instagram = itemView.findViewById(R.id.person_insta);
        }
    }
}
