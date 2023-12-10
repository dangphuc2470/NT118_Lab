package com.dnhp.lab5_btud;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;


public class DownloadTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "DownloadTask";
    private MediaPlayer mediaPlayer;

    public DownloadTask(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected String doInBackground(String... urls) {
        String url = urls[0];
        try {
            mediaPlayer.setDataSource(url); // Thiết lập nguồn dữ liệu là đường dẫn URL
            mediaPlayer.prepare(); // Chuẩn bị để phát âm thanh từ URL
        } catch (IOException e) {
            Log.e(TAG, "Lỗi tải file MP3: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        mediaPlayer.start(); // Bắt đầu phát âm thanh đã tải xuống
    }
}
