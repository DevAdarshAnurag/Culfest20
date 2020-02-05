package com.nitjsr.culfest20.adapters;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.activities.MainActivity;
import com.nitjsr.culfest20.models.Post;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Post> posts;

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
        Picasso.get().load(posts.get(position).getImage()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.loader.pauseAnimation();
                holder.loader.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                loadImage(holder, position);
            }
        });


        //lots of work to do......
        holder.nlikes.setText((posts.get(position).getLikes().size() - 1) + "");
        holder.description.setText(posts.get(position).getDescription());
        holder.time.setText(posts.get(position).getTimestamp());
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
                        popAnim(holder, position);
                        FirebaseDatabase.getInstance().getReference("posts").child(posts.get(position).getId()).child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(1);
                        holder.like.setImageResource(R.drawable.ic_hearts_grey);
                    }
                }
            });
        }
        else
        {
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Login as a user to like.",Toast.LENGTH_SHORT).show();
                }
            });
        }

        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage(posts.get(position).getImage(), posts.get(position).getTimestamp());
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.doubleClick && !posts.get(position).getLikes().containsKey(FirebaseAuth.getInstance().getUid())) {
                        popAnim(holder, position);
                        FirebaseDatabase.getInstance().getReference("posts").child(posts.get(position).getId()).child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(1);
                    } else {
                        holder.doubleClick = true;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.doubleClick = false;
                            }
                        }, 500);
                    }
                }
            });
        }

    }

    private void popAnim(MyViewHolder holder, int position) {
        Log.i("popAnim: ", "popped");
        holder.likeanim.setVisibility(View.VISIBLE);
        holder.heart.playAnimation();
        holder.heart.setSpeed(2f);
        holder.heart.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.i("onAnimationStart: ", "1");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Log.i("onAnimationEnd: ", "0");
                holder.likeanim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                holder.heart.pauseAnimation();
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    private void loadImage(MyViewHolder holder, int position) {
        Picasso.get().load(posts.get(position).getImage()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                // holder.image.setVisibility(View.VISIBLE);
                holder.loader.pauseAnimation();
                holder.loader.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                loadImage(holder, position);
            }
        });
    }

    private void downloadImage(String url, String timestamp) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        else {
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
            request.setTitle("Culfest'20 Image");
            request.setDescription("Downloading...");
            request.setMimeType("image/jpeg");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, File.separator + "Culfest20" + File.separator + filename);
            downloadManager.enqueue(request);
            Toast.makeText(context, "Downloading Image", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image, down;
        TextView title, nlikes, description, time;
        ImageView like;
        LottieAnimationView loader, heart;
        FrameLayout likeanim;
        boolean doubleClick;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            likeanim = itemView.findViewById(R.id.likeanim);
            heart = itemView.findViewById(R.id.heart);
            description = itemView.findViewById(R.id.description);
            loader = itemView.findViewById(R.id.loader);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            nlikes = itemView.findViewById(R.id.nlikes);
            down = itemView.findViewById(R.id.download);
            like = itemView.findViewById(R.id.like);
            time = itemView.findViewById(R.id.time_stamp);
            doubleClick = false;

        }
    }

}
