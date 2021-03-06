package edu.rit.se.milk.demoapp07;

import android.graphics.Color;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Button buttonPlay;
    Switch switchRendering;
    boolean vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        switchRendering = (Switch) findViewById(R.id.switchAccessibility);

        switchRendering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked) {
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                             //mute volume
                            mp.setVolume(0,0);

                        }
                    });
                }
                else
                {
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setVolume(100,100);
                        }
                    });
                }
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaController mediaController= new MediaController(MainActivity.this);
                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);

                //video file
                Uri uri = Uri.parse(String.format("android.resource://" + getPackageName() + "/" +R.raw.sample_video));

                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();

                videoView.start();
            }
        });
    }

}
