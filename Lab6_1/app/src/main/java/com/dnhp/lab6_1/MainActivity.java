package com.dnhp.lab6_1;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private MySmsReceiver broadcastReceiver;
    private IntentFilter filter;
    public TextView tvContent;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 123; // Request code

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcastReceiver();
        requestSmsPermission();
    }

    private void requestSmsPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
        }
    }

//    public void processReceive(Context context, Intent intent)
//    {
//        Toast.makeText(context, getString(R.string.you_have_a_new_message),
//                Toast.LENGTH_LONG).show();
//
//
//        // Use "pdus" as key to get message
//        final String SMS_EXTRA = "pdus";
//        Bundle bundle = intent.getExtras();
//        // Get array of messages which were received at the same time
//        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
//        String sms = "";
//
//        SmsMessage smsMsg;
//        for (int i = 0; i < messages.length; i++)
//        {
//            if (android.os.Build.VERSION.SDK_INT >= 23)
//            {
//                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i], "");
//            } else
//            {
//                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);
//            }
//
//            // Get message body
//            String msgBody = smsMsg.getMessageBody();
//            // Get source address of message
//            String address = smsMsg.getDisplayOriginatingAddress();
//            sms += address + ":\n" + msgBody + "\n";
//        }
//
//        // Show messages in text view
//        tvContent.setText(sms);
//    }


    private void initBroadcastReceiver()
    {
        // Create filter to listen to incoming messages
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // Create broadcastReceiver
        broadcastReceiver = new MySmsReceiver();
        broadcastReceiver.initTextView(findViewById(R.id.tv_content));
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Make sure broadcastReceiver was created
        if (broadcastReceiver == null)
        {
            initBroadcastReceiver();
        }

        // Register Receiver
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        // UnregisterReceiver when app is destroyed
        if (broadcastReceiver != null)
        {
            unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null; // Setting to null to avoid memory leaks
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_REQUEST_RECEIVE_SMS)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // Permission granted, register BroadcastReceiver
                registerReceiver(broadcastReceiver, filter);
            } else
            {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}