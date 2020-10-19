package com.example.syair20.Holder;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syair20.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class VideoHolder extends RecyclerView.ViewHolder {
    public PlayerView playerView;
    public SimpleExoPlayer exoPlayer; // Do this at top

    public VideoHolder(@NonNull View itemView) {
        super(itemView);
    }



    public  void setexoPlayer(Application application, String judul, String deskripsi, String url){
        TextView jud = itemView.findViewById(R.id.tetle);
        TextView des = itemView.findViewById(R.id.deskripsi);
        playerView = itemView.findViewById(R.id.vid);

        jud.setText(judul);
        des.setText(deskripsi);

        try{
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(application).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(application);
            Uri uri = Uri.parse(url);
           DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
           ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);
            playerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        }
        catch (Exception e){
            Log.e("ViewHolder", "exoplayer error"+e.toString());
        }
    }


}

