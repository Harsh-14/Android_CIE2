package com.fantappstic.musicplayer;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button play,previous,next;
    SeekBar seekMusicBar;
    Thread updateSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>22){
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE}, 1);
        }
        play=findViewById(R.id.BtnPlay);
        previous=findViewById(R.id.BtnPrevious);
        next=findViewById(R.id.BtnNext);
        seekMusicBar=findViewById(R.id.SeekBar);


        MediaPlayer mp=new MediaPlayer();
        try {
            mp.setDataSource(Environment.getExternalStorageDirectory().getPath()
                    +"/Music/"+"harsh.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying()){
                    play.setBackgroundResource(R.drawable.play_song_icon);
                    mp.pause();
                }else {
                    play.setBackgroundResource(R.drawable.pause_song_icon);
                    mp.start();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        updateSeekBar = new Thread() {
            @Override
            public void run() {

                int TotalDuration = mp.getDuration();
                int CurrentPosition = 0;

                while (CurrentPosition < TotalDuration) {
                    try {

                        sleep(500);
                        CurrentPosition = mp.getCurrentPosition();
                        seekMusicBar.setProgress(CurrentPosition);

                    } catch (InterruptedException | IllegalStateException e) {

                        e.printStackTrace();
                    }
                }

            }
        };


        seekMusicBar.setMax(mp.getDuration());
        updateSeekBar.start();


        seekMusicBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());
            }
        });

    }
}