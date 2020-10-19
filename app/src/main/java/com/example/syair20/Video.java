package com.example.syair20;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syair20.Holder.VideoHolder;
import com.example.syair20.Model.VideoAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Video extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase2;
    DatabaseReference mref2;
    public RecyclerView recyclerView;
    private VideoHolder videoHolder;
    private boolean playwhenready = false;
    private  int currentWindow = 0;
    private  long playbackposition = 0;
    boolean isActivityRunning = false;

    SimpleExoPlayer player;
    PlayerView playerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        recyclerView = findViewById(R.id.recyclevideo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase2 = FirebaseDatabase.getInstance();
        mref2 = mFirebaseDatabase2.getReference().child("Data User").child("Video");

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<VideoAdapter> options =
                new FirebaseRecyclerOptions.Builder<VideoAdapter>()
                .setQuery(mref2, VideoAdapter.class)
                .build();

        FirebaseRecyclerAdapter<VideoAdapter, VideoHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<VideoAdapter, VideoHolder>(options) {

                    @NonNull
                    @Override
                    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_video, parent, false);

                        return new VideoHolder(view);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull VideoHolder videoHolder, int i, @NonNull VideoAdapter videoAdapter) {
                        videoHolder.setexoPlayer(getApplication(), videoAdapter.getJudul(), videoAdapter.getDeskripsi(), videoAdapter.getUrl());
                    }
                };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }



}