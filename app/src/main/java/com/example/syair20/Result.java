package com.example.syair20;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Result extends AppCompatActivity {
    private TextView b, s, h,k;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        b = findViewById(R.id.benar);
        h = findViewById(R.id.total);
        k = findViewById(R.id.kal);
        final String bener = getIntent().getStringExtra("bener");
        final String seleh = getIntent().getStringExtra("seleh");
        final String jum = getIntent().getStringExtra("jumlah");
        b.setText(bener);
        k.setText(jum);


        double hb = Double.parseDouble(b.getText().toString());
        //  double hs = Double.parseDouble(s.getText().toString());
        double j = Double.parseDouble(k.getText().toString());
        // Toast.makeText(Result.this, jum, Toast.LENGTH_SHORT).show();
        double hasil = (hb/j)*100;
        h.setText(String.format("%.1f", hasil));
        h.setTextColor(Color.RED);
    }
}