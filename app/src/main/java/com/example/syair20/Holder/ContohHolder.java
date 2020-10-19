package com.example.syair20.Holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.syair20.Model.Contoh;
import com.example.syair20.R;

public class ContohHolder extends RecyclerView.ViewHolder {
    public TextView jud, jen;
    public ImageView gam;
    public CardView cardView;
    Context context;

    public ContohHolder(@NonNull View itemView) {
        super(itemView);
        jud = itemView.findViewById(R.id.judulcontoh);
        jen = itemView.findViewById(R.id.jeniscontoh);
        gam = itemView.findViewById(R.id.tampilangambarcontoh);
        cardView = itemView.findViewById(R.id.cardcontoh);
    }
    public void bindContoh(Contoh contoh, View.OnClickListener onClickListener){
        jud.setText(contoh.judul);
        jen.setText(contoh.jenis);
        String gs = contoh.getGambar();
        Glide.with(itemView.getContext())
               .load(gs)
                .into(gam);
        cardView.setOnClickListener(onClickListener);
    }
}
