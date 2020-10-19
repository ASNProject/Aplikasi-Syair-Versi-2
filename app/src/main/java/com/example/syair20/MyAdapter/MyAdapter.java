package com.example.syair20.MyAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.syair20.Model.Materi;
import com.example.syair20.R;
import com.example.syair20.SharePreference.SharePreference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    Context context;
    List<Materi> materiList;
    LayoutInflater inflater;
    SharePreference sessions;
    DatabaseReference firebaseDatabase;


    public MyAdapter(Context context, List<Materi> materiList) {
        this.context = context;
        this.materiList = materiList;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return materiList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View  view = inflater.inflate(R.layout.viewpager_item, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        ImageView gambar = (ImageView)view.findViewById(R.id.image2);
        TextView jud = (TextView)view.findViewById(R.id.textView2);
        TextView des = (TextView)view.findViewById(R.id.textView3);
        TextView lan = (TextView)view.findViewById(R.id.textView);
        sessions = new SharePreference(context.getApplicationContext());
        SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        Picasso.get().load(materiList.get(position).getLink()).into(gambar);
        jud.setText(materiList.get(position).getJudul());
        des.setText(materiList.get(position).getIsi());
        if (jud.length() == 21){
            lan.setText("< Kembali");
        }


        container.addView(view);
        return view;
    }
}
