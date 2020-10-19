package com.example.syair20;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.syair20.Listener.IFirebaseLoadDone;
import com.example.syair20.Model.Materi;
import com.example.syair20.MyAdapter.MyAdapter;
import com.example.syair20.SharePreference.SharePreference;
import com.example.syair20.Transformer.DepthPageTransformer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements IFirebaseLoadDone {
    ViewPager viewPager;
    MyAdapter adapter;
    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    VideoView videoView, videoView2, videoView3, videoView4, videoView5;
    CardView cardView, cardView2, cardView3, cardView4, cardView5, contohsoal, ujian;
    CardView c1, c2, c3, c4, c5;
    NestedScrollView nestedScrollView;
    TextView textView;

    DatabaseReference mDatabase, mDatabase2;
    IFirebaseLoadDone iFirebaseLoadDone;
    SharePreference sessions;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    TextView semuavideo, semuacontoh, a, b, c, d, e;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"gray\">" + getString(R.string.app_name)+ "</font>"));
        mDatabase =FirebaseDatabase.getInstance().getReference().child("Data User").child("ViewPager");
        mDatabase2 = FirebaseDatabase.getInstance().getReference();
        sessions = new SharePreference(MainActivity.this.getApplicationContext());
        cardView = findViewById(R.id.card1);
        cardView2 = findViewById(R.id.card2);
        cardView3 = findViewById(R.id.card3);
        cardView4 = findViewById(R.id.card4);
        cardView5 = findViewById(R.id.card5);
        nestedScrollView = findViewById(R.id.nestscroll);


        c1 = findViewById(R.id.con1);
        c2 = findViewById(R.id.con2);
        c3 = findViewById(R.id.con3);
        c4 = findViewById(R.id.con4);
        c5 = findViewById(R.id.con5);

        contohsoal = findViewById(R.id.cardcontohsoal);
        ujian = findViewById(R.id.cardtestsoal);

        semuavideo = findViewById(R.id.lihatlengkapvideo);
        semuacontoh = findViewById(R.id.lihatlengkapaudio);

        a = findViewById(R.id.textView11);
        b = findViewById(R.id.textView12);
        c = findViewById(R.id.textView13);
        d = findViewById(R.id.textView14);
        e = findViewById(R.id.textView15);

        iFirebaseLoadDone =this;


        loadMateri();
        tampilangambarcontoh();
      //  nestedScrollView = new NestedScrollView(this);
//        nestedScrollView.setBackgroundColor(Color.parseColor(color));


        semuavideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Video.class);
                startActivity(i);
            }
        });
        semuacontoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContohActivity.class);
                startActivity(i);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm();
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm2();
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm3();
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm4();
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm5();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contoh1();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contoh2();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contoh3();
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contoh4();
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contoh5();
            }
        });
        contohsoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child("Soal");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            final String jumlahsoal = snapshot.child("jumlah").getValue().toString();
                            Intent i = new Intent(MainActivity.this, Soal.class);
                            i.putExtra("j", jumlahsoal);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        ujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child("Ujian");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            final String jumlahsoal = snapshot.child("jumlah").getValue().toString();
                            Intent i = new Intent(MainActivity.this, Ujian.class);
                            i.putExtra("uj", jumlahsoal);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        viewPager = (ViewPager)findViewById(R.id.viewpager1);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_video, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Pemutar Video");
        dialog.setIcon(R.drawable.log1);
        videoView = (VideoView) dialogView.findViewById(R.id.playerview1);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String url = snapshot.child("Video").child("1").child("url").getValue().toString();
                    MediaController mediaController  = new MediaController(MainActivity.this);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    Log.d("Main", url);

                    Uri uri = Uri.parse(url);
                    videoView.setVideoURI(uri);
                    videoView.start();
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
    private void DialogForm2() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_video, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Pemutar Video");
        dialog.setIcon(R.drawable.log1);
        videoView = (VideoView) dialogView.findViewById(R.id.playerview1);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String url = snapshot.child("Video").child("2").child("url").getValue().toString();
                    MediaController mediaController  = new MediaController(MainActivity.this);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    Log.d("Main", url);

                    Uri uri = Uri.parse(url);
                    videoView.setVideoURI(uri);
                    videoView.start();
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
    private void DialogForm3() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_video, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Pemutar Video");
        dialog.setIcon(R.drawable.log1);
        videoView = (VideoView) dialogView.findViewById(R.id.playerview1);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String url = snapshot.child("Video").child("3").child("url").getValue().toString();
                    MediaController mediaController  = new MediaController(MainActivity.this);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    Log.d("Main", url);

                    Uri uri = Uri.parse(url);
                    videoView.setVideoURI(uri);
                    videoView.start();
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
    private void DialogForm4() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_video, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Pemutar Video");
        dialog.setIcon(R.drawable.log1);
        videoView = (VideoView) dialogView.findViewById(R.id.playerview1);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String url = snapshot.child("Video").child("4").child("url").getValue().toString();
                    MediaController mediaController  = new MediaController(MainActivity.this);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    Log.d("Main", url);

                    Uri uri = Uri.parse(url);
                    videoView.setVideoURI(uri);
                    videoView.start();
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
    private void DialogForm5() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_video, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Pemutar Video");
        dialog.setIcon(R.drawable.log1);
        videoView = (VideoView) dialogView.findViewById(R.id.playerview1);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String url = snapshot.child("Video").child("5").child("url").getValue().toString();
                    MediaController mediaController  = new MediaController(MainActivity.this);
                    videoView.setMediaController(mediaController);
                    mediaController.setAnchorView(videoView);
                    Log.d("Main", url);

                    Uri uri = Uri.parse(url);
                    videoView.setVideoURI(uri);
                    videoView.start();
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

    private void loadMateri() {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Materi> materiList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot materiSnapshot:snapshot.getChildren())
                    materiList.add(materiSnapshot.getValue(Materi.class));
                iFirebaseLoadDone.onFirebaseLoadSuccess(materiList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFirebaseLoadDone.onFirebaseLoadFailed(error.getMessage());
            }
        });
    }


    @Override
    public void onFirebaseLoadSuccess(List<Materi> materiList) {
        adapter = new MyAdapter(this, materiList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    private void Contoh1() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.item_contoh, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Contoh Syair");
        dialog.setIcon(R.drawable.log1);
        TextView juds = (TextView) dialogView.findViewById(R.id.judulcontoh);
        TextView isis = (TextView) dialogView.findViewById(R.id.isicontoh);
        ImageView g1 = (ImageView) dialogView.findViewById(R.id.gambarcontoh);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String jd = snapshot.child("Contoh").child("0").child("judul").getValue().toString();
                    String is = snapshot.child("Contoh").child("0").child("isi").getValue().toString();
                    String gm = snapshot.child("Contoh").child("0").child("gambar").getValue().toString();



                    Glide.with(MainActivity.this)
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
    private void Contoh2() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.item_contoh, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Contoh Syair");
        dialog.setIcon(R.drawable.log1);
        TextView juds = (TextView) dialogView.findViewById(R.id.judulcontoh);
        TextView isis = (TextView) dialogView.findViewById(R.id.isicontoh);
        ImageView g1 = (ImageView) dialogView.findViewById(R.id.gambarcontoh);

        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String jd = snapshot.child("Contoh").child("1").child("judul").getValue().toString();
                    String is = snapshot.child("Contoh").child("1").child("isi").getValue().toString();

                    juds.setText(jd);
                    isis.setText(is);
                    String gm = snapshot.child("Contoh").child("1").child("gambar").getValue().toString();
                    Glide.with(MainActivity.this)
                            .load(gm)
                            .into(g1);


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
    private void Contoh3() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.item_contoh, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Contoh Syair");
        dialog.setIcon(R.drawable.log1);
        TextView juds = (TextView) dialogView.findViewById(R.id.judulcontoh);
        TextView isis = (TextView) dialogView.findViewById(R.id.isicontoh);
        ImageView g1 = (ImageView) dialogView.findViewById(R.id.gambarcontoh);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String jd = snapshot.child("Contoh").child("2").child("judul").getValue().toString();
                    String is = snapshot.child("Contoh").child("2").child("isi").getValue().toString();

                    juds.setText(jd);
                    isis.setText(is);
                    String gm = snapshot.child("Contoh").child("2").child("gambar").getValue().toString();



                    Glide.with(MainActivity.this)
                            .load(gm)
                            .into(g1);

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
    private void Contoh4() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.item_contoh, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Contoh Syair");
        dialog.setIcon(R.drawable.log1);
        TextView juds = (TextView) dialogView.findViewById(R.id.judulcontoh);
        TextView isis = (TextView) dialogView.findViewById(R.id.isicontoh);
        ImageView g1 = (ImageView) dialogView.findViewById(R.id.gambarcontoh);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String jd = snapshot.child("Contoh").child("3").child("judul").getValue().toString();
                    String is = snapshot.child("Contoh").child("3").child("isi").getValue().toString();

                    juds.setText(jd);
                    isis.setText(is);
                    String gm = snapshot.child("Contoh").child("3").child("gambar").getValue().toString();



                    Glide.with(MainActivity.this)
                            .load(gm)
                            .into(g1);

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
    private void Contoh5() {
        dialog = new AlertDialog.Builder(MainActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.item_contoh, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Contoh Syair");
        dialog.setIcon(R.drawable.log1);
        TextView juds = (TextView) dialogView.findViewById(R.id.judulcontoh);
        TextView isis = (TextView) dialogView.findViewById(R.id.isicontoh);
        ImageView g1 = (ImageView) dialogView.findViewById(R.id.gambarcontoh);
        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String jd = snapshot.child("Contoh").child("4").child("judul").getValue().toString();
                    String is = snapshot.child("Contoh").child("4").child("isi").getValue().toString();

                    juds.setText(jd);
                    isis.setText(is);
                    String gm = snapshot.child("Contoh").child("4").child("gambar").getValue().toString();



                    Glide.with(MainActivity.this)
                            .load(gm)
                            .into(g1);

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

    void tampilangambarcontoh(){
        ImageView gc1 = (ImageView) findViewById(R.id.im1);
        TextView t1 = (TextView) findViewById(R.id.judulaudio1);
        TextView k1 = (TextView) findViewById(R.id.karyaaudio1);
        ImageView gc2 = (ImageView) findViewById(R.id.im2);
        TextView t2 = (TextView) findViewById(R.id.judulaudio2);
        TextView k2 = (TextView) findViewById(R.id.karyaaudio2);
        ImageView gc3 = (ImageView) findViewById(R.id.im3);
        TextView t3 = (TextView) findViewById(R.id.judulaudio3);
        TextView k3 = (TextView) findViewById(R.id.karyaaudio3);
        ImageView gc4 = (ImageView) findViewById(R.id.im4);
        TextView t4 = (TextView) findViewById(R.id.judulaudio4);
        TextView k4 = (TextView) findViewById(R.id.karyaaudio4);
        ImageView gc5 = (ImageView) findViewById(R.id.im5);
        TextView t5 = (TextView) findViewById(R.id.judulaudio5);
        TextView k5 = (TextView) findViewById(R.id.karyaaudio5);


        mDatabase2.child("Data User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String jdl = snapshot.child("Contoh").child("0").child("judul").getValue().toString();
                    String jns = snapshot.child("Contoh").child("0").child("jenis").getValue().toString();
                    String gm = snapshot.child("Contoh").child("0").child("gambar").getValue().toString();
                    t1.setText(jdl);
                    k1.setText(jns);
                    Glide.with(MainActivity.this)
                            .load(gm)
                            .into(gc1);
                    String jdl2 = snapshot.child("Contoh").child("1").child("judul").getValue().toString();
                    String jns2 = snapshot.child("Contoh").child("1").child("jenis").getValue().toString();
                    String gm2 = snapshot.child("Contoh").child("1").child("gambar").getValue().toString();
                    t2.setText(jdl2);
                    k2.setText(jns2);
                    Glide.with(MainActivity.this)
                            .load(gm2)
                            .into(gc2);
                    String jdl3 = snapshot.child("Contoh").child("2").child("judul").getValue().toString();
                    String jns3 = snapshot.child("Contoh").child("2").child("jenis").getValue().toString();
                    String gm3 = snapshot.child("Contoh").child("2").child("gambar").getValue().toString();
                    t3.setText(jdl3);
                    k3.setText(jns3);
                    Glide.with(MainActivity.this)
                            .load(gm3)
                            .into(gc3);
                    String jdl4 = snapshot.child("Contoh").child("3").child("judul").getValue().toString();
                    String jns4 = snapshot.child("Contoh").child("3").child("jenis").getValue().toString();
                    String gm4 = snapshot.child("Contoh").child("3").child("gambar").getValue().toString();
                    t4.setText(jdl4);
                    k4.setText(jns4);
                    Glide.with(MainActivity.this)
                            .load(gm4)
                            .into(gc4);
                    String jdl5= snapshot.child("Contoh").child("4").child("judul").getValue().toString();
                    String jns5 = snapshot.child("Contoh").child("4").child("jenis").getValue().toString();
                    String gm5 = snapshot.child("Contoh").child("4").child("gambar").getValue().toString();
                    t5.setText(jdl5);
                    k5.setText(jns5);
                    Glide.with(MainActivity.this)
                            .load(gm5)
                            .into(gc5);

                    String juds1 = snapshot.child("Video").child("1").child("judul").getValue().toString();
                    String juds2 = snapshot.child("Video").child("2").child("judul").getValue().toString();
                    String juds3 = snapshot.child("Video").child("3").child("judul").getValue().toString();
                    String juds4 = snapshot.child("Video").child("4").child("judul").getValue().toString();
                    String juds5 = snapshot.child("Video").child("5").child("judul").getValue().toString();
                    a.setText(juds1);
                    b.setText(juds2);
                    c.setText(juds3);
                    d.setText(juds4);
                    e.setText(juds5);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}