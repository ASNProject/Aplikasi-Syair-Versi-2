package com.example.syair20;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.syair20.Holder.ContohHolder;
import com.example.syair20.Model.Contoh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ContohActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Contoh, ContohHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.recycleconoh);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Contoh>()
                .setQuery(query, Contoh.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Contoh, ContohHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ContohHolder contohHolder, int i, @NonNull Contoh contoh) {
                contohHolder.bindContoh(contoh, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = String.valueOf(i);
                        dialog = new AlertDialog.Builder(ContohActivity.this);
                        inflater = getLayoutInflater();
                        dialogView = inflater.inflate(R.layout.item_contoh, null);
                        dialog.setView(dialogView);
                        dialog.setCancelable(true);
                        dialog.setTitle("Contoh Syair");
                        dialog.setIcon(R.drawable.log1);
                        TextView juds = (TextView) dialogView.findViewById(R.id.judulcontoh);
                        TextView isis = (TextView) dialogView.findViewById(R.id.isicontoh);
                        ImageView g1 = (ImageView) dialogView.findViewById(R.id.gambarcontoh);

                        mDatabase.child("Data User").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                    String jd = snapshot.child("Contoh").child(id).child("judul").getValue().toString();
                                    String is = snapshot.child("Contoh").child(id).child("isi").getValue().toString();
                                    String gm = snapshot.child("Contoh").child(id).child("gambar").getValue().toString();
                                    Glide.with(ContohActivity.this)
                                            .load(gm)
                                            .into(g1);

                                    juds.setText(jd);
                                    isis.setText(is);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        dialog.setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                });
            }

            @NonNull
            @Override
            public ContohHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new ContohHolder(inflater.inflate(R.layout.contoh_item, parent, false));
            }
        };
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        Query query = mDatabase.child("Data User").child("Contoh");
        return query;
    }
}