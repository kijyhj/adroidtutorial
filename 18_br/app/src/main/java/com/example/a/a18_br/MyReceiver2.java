package com.example.a.a18_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

        Toast.makeText(context, "batt : " + level, Toast.LENGTH_SHORT).show();
    }
}
