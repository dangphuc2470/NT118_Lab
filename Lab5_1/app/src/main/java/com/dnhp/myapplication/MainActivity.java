package com.dnhp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dnhp.myapplication.databinding.ActivityMainBinding;

import android.os.Handler;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC;
    private int intTest;
    private Thread bgThread;
    private Handler handler;
    private Button btnStart;
    ActivityMainBinding AMBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AMBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(AMBinding.getRoot());

        // Gọi phương thức để tìm các view và khởi tạo biến
        findViewByIds();
        initVariables();

        // Xử lý sự kiện click cho button
        btnStart.setOnClickListener(v ->
        {
            isRunning = true;
            pbFirst.setVisibility(View.VISIBLE);
            pbSecond.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.GONE);
            bgThread.start();
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }



    private void findViewByIds()
    {
        pbFirst = AMBinding.pbFirst;
        pbSecond = AMBinding.pbSecond;
        tvMsgWorking = AMBinding.tvWorking;
        tvMsgReturned = AMBinding.tvReturn;
        btnStart = AMBinding.btnStart;
    }


    private void initVariables()
    {
        isRunning = false;
        MAX_SEC = 20;
        intTest = 1;
        pbFirst.setMax(MAX_SEC);
        pbFirst.setProgress(0);
        // Init Views
        pbFirst.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);
        handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                String returnedValue = (String) msg.obj;
                // Do something with the value sent by the background thread here...
                tvMsgReturned.setText(getString(R.string.returned_by_bg_thread) + returnedValue);
                pbFirst.incrementProgressBy(2);
                // Testing thread's termination
                if (pbFirst.getProgress() == MAX_SEC)
                {
                    tvMsgReturned.setText(getString(R.string.done_background_thread_has_been_stopped));
                    tvMsgWorking.setText(getString(R.string.done));
                    pbFirst.setVisibility(View.GONE);
                    pbSecond.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                    isRunning = false;
                } else
                {
                    tvMsgWorking.setText(getString(R.string.working) + pbFirst.getProgress());
                }
            }
        };
    }

    private void initBgThread()
    {
        bgThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    for (int i = 0; i < MAX_SEC && isRunning; i++)
                    {
                        // Sleep one second
                        Thread.sleep(1000);
                        Random rnd = new Random();
                        // This is a locally generated value
                        String data = "Thread value: " + rnd.nextInt(101);
                        // We can see chance (global) class variables
                        data += getString(R.string.global_value_seen) + " " + intTest;
                        intTest++;
                        // If thread is still alive, send the message
                        if (isRunning)
                        {
                            // Request a message token and put some data in it
                            Message msg = handler.obtainMessage(1, data);
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Throwable t)
                {
                    // Handle exceptions or errors here
                }
            }
        });
    }


}