package com.example.syair20;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.syair20.Model.SoalAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ujian extends AppCompatActivity {
    private TextView soals, pancing;
    private Button a, b, c, d;
    private Button cek;
    int total = 1;
    int benar = 0;
    int salah = 0;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ujian);
        soals = findViewById(R.id.soalujian);
        a = findViewById(R.id.opsi1ujian);
        b = findViewById(R.id.opsi2ujian);
        c = findViewById(R.id.opsi3ujian);
        d = findViewById(R.id.opsi4ujian);



        mulaipertanyaan();
    }

    private void mulaipertanyaan() {
        final String jum = getIntent().getStringExtra("uj");
        int jumlah = Integer.parseInt(jum);
        if (total > jumlah ){
            String bener = String.valueOf(benar);
            String seleh = String.valueOf(salah);
            Intent is = new Intent(Ujian.this, Result.class);
            is.putExtra("bener", bener);
            is.putExtra("seleh", seleh);
            is.putExtra("jumlah", jum);
            startActivity(is);
            finish();
        }
        else {
            String data = String.valueOf(total);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Data User").child("Ujian").child(data);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final SoalAdapter soalAdapter = snapshot.getValue(SoalAdapter.class);
                    soals.setText(soalAdapter.getPertanyaan());
                    a.setText(soalAdapter.getOpsi1());
                    b.setText(soalAdapter.getOpsi2());
                    c.setText(soalAdapter.getOpsi3());
                    d.setText(soalAdapter.getOpsi4());
                    a.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(a.getText().toString().equals(soalAdapter.getJawaban())){
                                a.setBackgroundColor(Color.GREEN);
                                total++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        benar++;
                                        a.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        mulaipertanyaan();
                                    }
                                },1500);
                            }
                            else {
                                salah++;
                                total++;
                                a.setBackgroundColor(Color.RED);
                                if(b.getText().toString().equals(soalAdapter.getJawaban())){
                                    b.setBackgroundColor(Color.GREEN);
                                }
                                else if (c.getText().toString().equals(soalAdapter.getJawaban())){
                                    c.setBackgroundColor(Color.GREEN);
                                }
                                else if (d.getText().toString().equals(soalAdapter.getJawaban())){
                                    d.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler= new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        a.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        c.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        d.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        mulaipertanyaan();
                                    }
                                },1500);

                            }
                        }
                    });
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(b.getText().toString().equals(soalAdapter.getJawaban())){
                                b.setBackgroundColor(Color.GREEN);
                                total++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        benar++;
                                        b.setBackgroundColor(Color.parseColor("#03A9F4"));

                                        mulaipertanyaan();
                                    }
                                },1500);
                            }
                            else {
                                total++;
                                salah++;
                                b.setBackgroundColor(Color.RED);
                                if(a.getText().toString().equals(soalAdapter.getJawaban())){
                                    a.setBackgroundColor(Color.GREEN);
                                }
                                else if (c.getText().toString().equals(soalAdapter.getJawaban())){
                                    c.setBackgroundColor(Color.GREEN);
                                }
                                else if (d.getText().toString().equals(soalAdapter.getJawaban())){
                                    d.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler= new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        a.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        c.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        d.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        mulaipertanyaan();
                                    }
                                },1500);

                            }
                        }
                    });
                    c.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(c.getText().toString().equals(soalAdapter.getJawaban())){
                                c.setBackgroundColor(Color.GREEN);
                                total++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        benar++;
                                        c.setBackgroundColor(Color.parseColor("#03A9F4"));

                                        mulaipertanyaan();
                                    }
                                },1500);
                            }
                            else {
                                salah++;
                                total++;
                                c.setBackgroundColor(Color.RED);
                                if(b.getText().toString().equals(soalAdapter.getJawaban())){
                                    b.setBackgroundColor(Color.GREEN);
                                }
                                else if (a.getText().toString().equals(soalAdapter.getJawaban())){
                                    a.setBackgroundColor(Color.GREEN);
                                }
                                else if (d.getText().toString().equals(soalAdapter.getJawaban())){
                                    d.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler= new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        a.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        c.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        d.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        mulaipertanyaan();
                                    }
                                },1500);

                            }
                        }
                    });
                    d.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(d.getText().toString().equals(soalAdapter.getJawaban())){
                                d.setBackgroundColor(Color.GREEN);
                                total++;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        benar++;
                                        d.setBackgroundColor(Color.parseColor("#03A9F4"));

                                        mulaipertanyaan();
                                    }
                                },1500);
                            }
                            else {
                                salah++;
                                total++;
                                d.setBackgroundColor(Color.RED);
                                if(b.getText().toString().equals(soalAdapter.getJawaban())){
                                    b.setBackgroundColor(Color.GREEN);
                                }
                                else if (c.getText().toString().equals(soalAdapter.getJawaban())){
                                    c.setBackgroundColor(Color.GREEN);
                                }
                                else if (a.getText().toString().equals(soalAdapter.getJawaban())){
                                    a.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler= new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        a.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        b.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        c.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        d.setBackgroundColor(Color.parseColor("#03A9F4"));
                                        mulaipertanyaan();
                                    }
                                },1500);

                            }
                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}