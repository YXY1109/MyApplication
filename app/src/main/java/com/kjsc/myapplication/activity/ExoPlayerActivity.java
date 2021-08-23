package com.kjsc.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.kjsc.myapplication.databinding.ActivityExoBinding;

public class ExoPlayerActivity extends AppCompatActivity implements Player.Listener {
    private ActivityExoBinding binding;
    private SimpleExoPlayer player;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        player = new SimpleExoPlayer.Builder(ExoPlayerActivity.this).build();
        player.addListener(this);
        binding.plyaerView.setPlayer(player);

        //播放视频
        String videoUri = "https://v-cdn.zjol.com.cn/276982.mp4";

        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
    }


    @Override
    public void onPlayerError(PlaybackException error) {
        Log.i("yxy", "onPlayerError:" + error.getMessage());
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Log.i("yxy", "onPlayerStateChanged:playWhenReady:" + playWhenReady);
        Log.i("yxy", "onPlayerStateChanged: playbackState:" + playbackState);
        //Player.STATE_IDLE
    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {
        Log.i("yxy", "onIsPlayingChanged::" + isPlaying);
    }
}
