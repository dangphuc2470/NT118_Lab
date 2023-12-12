package com.dnhp.lab6_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity
{
    private ReentrantLock reentrantLock;
    private Switch swAutoResponse;
    private LinearLayout llButtons;
    private Button btnSafe, btnMayday;
    private ArrayList<String> requesters;
    private ArrayAdapter<String> adapter;
    private ListView lvMessages;
    private BroadcastReceiver broadcastReceiver;
    public static boolean isRunning;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private final String AUTO_RESPONSE = "auto_response";


    private void findViewsByIds()
    {
        swAutoResponse = findViewById(R.id.sw_auto_response);
        llButtons = findViewById(R.id.ll_buttons);
        lvMessages = findViewById(R.id.lv_messages);
        btnSafe = findViewById(R.id.btn_safe);
        btnMayday = findViewById(R.id.btn_mayday);
    }

    private void respond(String to, String response)
    {
        reentrantLock.lock();
        requesters.remove(to);
        adapter.notifyDataSetChanged();
        reentrantLock.unlock();

        // Send the message
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(to, null, response, null, null);
        Toast.makeText(this, "To "+ to + "Message sent!", Toast.LENGTH_SHORT).show();
    }

    public void respond(boolean ok)
    {
        String okString = getString(R.string.i_am_safe_and_well_worry_not);
        String notOkString = getString(R.string.tell_my_mother_i_love_her);
        String outputString = ok ? okString : notOkString;

        ArrayList<String> requestersCopy = (ArrayList<String>) requesters.clone();
        for (String to : requestersCopy)
        {
            respond(to, outputString);
        }
    }

    public void processReceiveAddresses(ArrayList<String> addresses)
    {
        for (int i = 0; i < addresses.size(); i++)
        {
            if (!requesters.contains(addresses.get(i)))
            {
                reentrantLock.lock();
                requesters.add(addresses.get(i));
                adapter.notifyDataSetChanged();
                reentrantLock.unlock();

                // Check to response automatically
                if (swAutoResponse.isChecked())
                {
                    respond(true);
                }
            }
        }
    }


    private void handleOnClickListener()
    {
        // Handle onClickListener for btnSafe
        btnSafe.setOnClickListener(view -> respond(true));

        // Handle onClickListener for btnMayday
        btnMayday.setOnClickListener(view -> respond(false));

        // Set OnCheckedChangeListener for swAutoResponse
        swAutoResponse.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            if (isChecked)
            {
                llButtons.setVisibility(View.GONE);
            } else
            {
                llButtons.setVisibility(View.VISIBLE);
            }

            // Save auto response setting
            editor.putBoolean(AUTO_RESPONSE, isChecked);
            editor.commit();

        });
    }

    private void initBroadcastReceiver()
    {
        broadcastReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                // Get ArrayList addresses
                ArrayList<String> addresses = intent.getStringArrayListExtra(SmsReceiver.SMS_MESSAGE_ADDRESS_KEY);

                // Process these addresses
                processReceiveAddresses(addresses);
            }
        };
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        isRunning = true;

        // Make sure broadcastReceiver was initialized
        if (broadcastReceiver == null)
        {
            initBroadcastReceiver();
        }

        // RegisterReceiver
        IntentFilter intentFilter = new IntentFilter(SmsReceiver.SMS_FORWARD_BROADCAST_RECEIVER);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        isRunning = false;

        // UnregisterReceiver
        unregisterReceiver(broadcastReceiver);
    }

    private void initVariables()
    {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();
        reentrantLock = new ReentrantLock();
        requesters = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, requesters);
        lvMessages.setAdapter(adapter);

        // Load auto response setting
        boolean autoResponse = sharedPreferences.getBoolean(AUTO_RESPONSE, false);
        swAutoResponse.setChecked(autoResponse);
        if (autoResponse)
        {
            llButtons.setVisibility(View.GONE);
        }

        initBroadcastReceiver();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsByIds();
        initVariables();
        requestSmsPermission();
        handleOnClickListener();
    }
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 123; // Request code
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 123; // Request code
    private void requestSmsPermission()
    {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS},
                    MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }


//
//    private void checkPermissions()
//    {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
//                != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
//                        != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED)
//        {
//            // Permissions not granted, request them
//            requestPermissions();
//        } else
//        {
//            // Permissions already granted
//            // Proceed with your app logic
//        }
//    }
//
//    private void requestPermissions()
//    {
//        ActivityCompat.requestPermissions(this, new String[]{
//                Manifest.permission.RECEIVE_SMS,
//                Manifest.permission.SEND_SMS,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//        }, PERMISSION_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
//    {
//        if (requestCode == PERMISSION_REQUEST_CODE)
//        {
//            boolean allPermissionsGranted = true;
//            for (int result : grantResults)
//            {
//                if (result != PackageManager.PERMISSION_GRANTED)
//                {
//                    allPermissionsGranted = false;
//                    break;
//                }
//            }
//            if (allPermissionsGranted)
//            {
//                // Permissions granted
//                // Proceed with your app logic
//            } else
//            {
//                // Permissions denied
//                // Handle the scenario when permissions are denied
//            }
//        }
//    }
//
//    // Broadcast receiver to handle incoming SMS messages
//    private BroadcastReceiver smsReceiver = new BroadcastReceiver()
//    {
//        @Override
//        public void onReceive(Context context, Intent intent)
//        {
//            // Handle incoming SMS message
//        }
//    };
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        // Register the SMS receiver
//        IntentFilter intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
//        registerReceiver(smsReceiver, intentFilter);
//    }
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        // Unregister the SMS receiver
//        unregisterReceiver(smsReceiver);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
}