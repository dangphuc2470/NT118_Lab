package com.dnhp.lab6_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerStateChangeReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();

        if (action != null && action.equals(Intent.ACTION_POWER_CONNECTED))
        {
            Toast.makeText(context, "Thiết bị đang sạc", Toast.LENGTH_SHORT).show();
        } else if (action != null && action.equals(Intent.ACTION_POWER_DISCONNECTED))
        {
            Toast.makeText(context, "Thiết bị không còn sạc", Toast.LENGTH_SHORT).show();
        }
    }
}