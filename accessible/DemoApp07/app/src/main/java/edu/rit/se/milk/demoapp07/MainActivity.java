package edu.rit.se.milk.demoapp07;

import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Button buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaController mediaController= new MediaController(MainActivity.this);
                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);

                //video file
                Uri uri = Uri.parse(String.format("android.resource://" + getPackageName() + "/" +R.raw.sample_video));
                //captions file
                InputStream subs =  getResources().openRawResource(R.raw.sample_video_caption);

                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.addSubtitleSource(subs, MediaFormat.createSubtitleFormat("text/vtt", Locale.ENGLISH.getLanguage()));

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mp.setVolume(100,100);
                    }
                });


                videoView.start();

            }
        });
    }

}
