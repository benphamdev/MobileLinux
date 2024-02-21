package com.example.bai128mediaonline;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnMp4;
    ProgressBar progressBar;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        setupProcess();
    }

    private void setupProcess() {
        setupMp3();
        setupMp4();
    }

    public void setupMp4(){
        btnMp4.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
            progressBar.setVisibility(View.VISIBLE);
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(MainActivity.this));
            progressBar.setVisibility(View.GONE);
            videoView.start();
        });
    }
    public void setupMp3(){
        btnPlay.setOnClickListener(v -> {
            String url = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3";
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();
                progressBar.setVisibility(View.VISIBLE);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        progressBar.setVisibility(View.GONE);
                        mp.start();
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void setupUI() {
        btnPlay = findViewById(R.id.button);
        btnMp4 = findViewById(R.id.button_mp4);
        progressBar = findViewById(R.id.progressBar_load);
        videoView = findViewById(R.id.videoView);
        progressBar.setVisibility(View.GONE);
    }
}