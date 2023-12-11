package com.dnhp.lab6_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver
{

    public static final String pdu_type = "pdus";

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_messages_key";


    public void onReceiveco(Context context, Intent intent)
    {
        String queryString = "Are you OK?".toLowerCase();

        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++)
            {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], "");
            }

            // Create ArrayList of OriginatingAddress of messages which contain queryString
            ArrayList<String> addresses = new ArrayList<>();

            for (SmsMessage message : messages)
            {
                try
                {
                    if (message != null && message.getMessageBody() != null)
                    {
                        Toast.makeText(context.getApplicationContext(), message.getMessageBody(), Toast.LENGTH_SHORT).show();
                        if (message.getMessageBody().toLowerCase().contains(queryString))
                        {
                            addresses.add(message.getOriginatingAddress());
                        }
                    }
                } catch (Exception e)
                {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            if (addresses.size() > 0)
            {
                if (!MainActivity.isRunning)
                {
                    // Start MainActivity if it stopped
                    // Your code to start MainActivity goes here
                } else
                {
                    // Forward these addresses to MainActivity to process
                    Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                    iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.sendBroadcast(iForwardBroadcastReceiver);
                }
            }

        }
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // Get the SMS message.
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");
            String format = bundle.getString("format");
            if (pdus != null && pdus.length > 0)
            {
                ArrayList<String> addresses = new ArrayList<>();
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++)
                {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    String messageBody = messages[i].getMessageBody();
                    if (messageBody.toLowerCase().contains("are you ok?"))
                    {
                        addresses.add(messages[i].getOriginatingAddress());
                        // Display a toast message.
                        Toast.makeText(context, "Received message: " + messageBody, Toast.LENGTH_LONG).show();
                        if (addresses.size() > 0)
                        {
                            Intent forwardIntent = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                            forwardIntent.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                            context.sendBroadcast(forwardIntent);
                        }
                    }
                }
            }
        }
    }
    public void onReceive2(Context context, Intent intent) {
        String queryString = "Are you OK?".toLowerCase();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null && pdus.length > 0) {
                ArrayList<String> addresses = new ArrayList<>();
                for (Object pdu : pdus) {
                    SmsMessage smsMessage;
                    if (Build.VERSION.SDK_INT >= 23) {
                        smsMessage = SmsMessage.createFromPdu((byte[]) pdu, "");
                    } else {
                        smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                    }
                    String messageBody = smsMessage.getMessageBody().toLowerCase();
                    if (messageBody.contains(queryString)) {
                        addresses.add(smsMessage.getOriginatingAddress());
                    }
                }
                if (addresses.size() > 0) {
                    Intent forwardIntent = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                    forwardIntent.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.sendBroadcast(forwardIntent);
                }
            }
        }
    }

//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//        // Get the SMS message.
//        Bundle bundle = intent.getExtras();
//        SmsMessage[] msgs;
//        String strMessage = "";
//        String format = bundle.getString("format");
//        // Retrieve the SMS message received.
//        Object[] pdus = (Object[]) bundle.get(pdu_type);
//        if (pdus != null)
//        {
//            // Fill the msgs array.
//            msgs = new SmsMessage[pdus.length];
//            for (int i = 0; i < msgs.length; i++)
//            {
//                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
//                // Build the message to show.
//                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
//                strMessage += " :\n" + msgs[i].getMessageBody() + "\n";
//
//
//                // Log and display the SMS message.
//                Log.d("123", "onReceive: " + strMessage);
//                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
//
//            }
//        }
//
//
//    }

}
