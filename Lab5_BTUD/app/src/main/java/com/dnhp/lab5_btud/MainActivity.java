package com.dnhp.lab5_btud;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    public static Button btPlay;

    private DownloadTask downloadTask;
    private MediaPlayer mediaPlayer;
    boolean downloaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPlay = findViewById(R.id.btPlay);
        mediaPlayer = new MediaPlayer();



        btPlay.setOnClickListener(v ->
        {
            if (!downloaded)
            {
                btPlay.setText("Downloading...");
                downloadTask = new DownloadTask(mediaPlayer);
                downloadTask.execute("https://od.lk/s/MjdfMjI2NDkxODRf/Compass%20Feat%20Merethe%20Soltvedt%20Miracles.mp3");
                downloaded = true;
            }
            else if (mediaPlayer.isPlaying())
            {
                mediaPlayer.pause();
                btPlay.setText("Play");
            }
            else
            {
                mediaPlayer.start();
                btPlay.setText("Pause");
            }
        });
    }
}