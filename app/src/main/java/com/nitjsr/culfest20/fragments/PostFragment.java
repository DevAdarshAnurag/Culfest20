package com.nitjsr.culfest20.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nitjsr.culfest20.R;
import com.nitjsr.culfest20.adapters.PostAdapter;
import com.nitjsr.culfest20.models.Post;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {


    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;
    private LinearLayout youtubeBox;
    private ArrayList<Post> posts = new ArrayList<>();

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerViewPost = view.findViewById(R.id.rv_post);
        PostAdapter postAdapter = new PostAdapter(getActivity(), posts);
        recyclerViewPost.setAdapter(postAdapter);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseDatabase.getInstance().getReference("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Post temp = new Post();
                    temp.setId(ds.child("id").getValue(String.class));
                    temp.setTimestamp(ds.child("timestamp").getValue(String.class));
                    temp.setTitle(ds.child("title").getValue(String.class));
                    temp.setDescription(ds.child("description").getValue(String.class));
                    temp.setImage(ds.child("image").getValue(String.class));
                    for (DataSnapshot dss : ds.child("likes").getChildren()) {
                        temp.getLikes().put(dss.getKey(), dss.getValue(Integer.class));
                    }
                    posts.add(temp);
                }

                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //there is no error in line below
        youTubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.youtube_fragment);
        youtubeBox = view.findViewById(R.id.youtube_box);
        initializeYouTube();

        view.findViewById(R.id.youtube_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtubeBox.getVisibility() == View.GONE) {
                    youtubeBox.setVisibility(View.VISIBLE);
                    initializeYouTube();
                } else {
                    youtubeBox.setVisibility(View.GONE);
                    youTubePlayerFragment.onPause();
                    youTubePlayer.release();
                }
            }
        });

    }

    private void initializeYouTube() {
        if (youTubePlayerFragment != null) {
            youTubePlayerFragment.initialize("AIzaSyB5QCNjhNqOoK7nT8aKhMLKcQewkY3jacM", new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                    boolean wasRestored) {
                    if (!wasRestored) {
                        youTubePlayer = player;
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        youTubePlayer.cuePlaylist("BqgEDu3HRX1bFC6_WDbNVC2BZxMVvbXG");
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                    Log.e("youtube", "Youtube Player View initialization failed");
                }
            });
        }
    }
}
