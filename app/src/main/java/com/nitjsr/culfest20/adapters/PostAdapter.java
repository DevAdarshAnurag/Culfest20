package com.nitjsr.culfest20.adapters;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.models.Post;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context context;
    ArrayList<Post> posts;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.title.setText(posts.get(position).getTitle());
        Picasso.get().load(posts.get(position).getImage()).placeholder(R.drawable.bg_culfest_post).networkPolicy(NetworkPolicy.OFFLINE).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(posts.get(position).getImage()).into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        //Toast.makeText(context,"Unable to load Image",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        //lots of work to do......
        holder.nlikes.setText((posts.get(position).getLikes().size() - 1)+"");
        holder.description.setText(posts.get(position).getDescription());
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if (posts.get(position).getLikes().containsKey(FirebaseAuth.getInstance().getUid())) {
                holder.like.setImageResource(R.drawable.ic_hearts_red);
            } else
                holder.like.setImageResource(R.drawable.ic_hearts_grey);
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (posts.get(position).getLikes().containsKey(FirebaseAuth.getInstance().getUid())) {
                        FirebaseDatabase.getInstance().getReference("posts").child(posts.get(position).getId()).child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(null);
                        holder.like.setImageResource(R.drawable.ic_hearts_red);
                    } else {
                        FirebaseDatabase.getInstance().getReference("posts").child(posts.get(position).getId()).child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(1);
                        Log.i("1", posts.get(position).getId());
                        holder.like.setImageResource(R.drawable.ic_hearts_grey);
                    }
                }
            });
        }

        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage(posts.get(position).getImage(), posts.get(position).getTimestamp());
            }
        });

    }

    private void downloadImage(String url, String timestamp) {

        String filename = "culfest20" + timestamp + ".jpg";

        File direct = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/" + "Culfest20" + "/");
        if (!direct.exists()) {
            direct.mkdir();
            Log.i("DIR", "directory created");
        } else {
            Log.i("DIR", "directory present");
        }
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Culfest Post");
        request.setDescription("Downloading");
        request.setMimeType("image/jpeg");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, File.separator + "Culfest20" + File.separator + filename);
        downloadManager.enqueue(request);
        Toast.makeText(context, "Downloading Image", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image, down;
        TextView title, nlikes;
        TextView description;
        ImageView like;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            nlikes = itemView.findViewById(R.id.nlikes);
            down = itemView.findViewById(R.id.download);
            like = itemView.findViewById(R.id.like);
        }
    }

}
