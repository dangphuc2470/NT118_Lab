package com.dnhp.lab6_1;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver
{
    private static final String TAG =
            MySmsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";
    private String message;
    private TextView textView;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // Get the SMS message.
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null)
        {
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                // Build the message to show.
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :\n" + msgs[i].getMessageBody() + "\n";

                if (textView != null)
                {
                    textView.setText(textView.getText() + strMessage + "\n");
                }

                // Log and display the SMS message.
                Log.d(TAG, "onReceive: " + strMessage);
                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();

            }
        }


    }

    public void initTextView(TextView textView)
    {
        this.textView = textView;
    }
}