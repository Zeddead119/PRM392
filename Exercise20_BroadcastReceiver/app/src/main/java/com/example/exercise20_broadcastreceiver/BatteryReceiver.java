package com.example.exercise20_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryReceiver";
    private TextView txtBattery;

    public BatteryReceiver(TextView txtBattery) {
        this.txtBattery = txtBattery;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(intent.ACTION_BATTERY_CHANGED.equals(action)){
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level / (float) scale * 100;
            Toast.makeText(context, "Battery level changed: " + batteryPct + "%", Toast.LENGTH_LONG).show();
            txtBattery.setText("Your battery level is: " + batteryPct + "%");
        }
    }
}
