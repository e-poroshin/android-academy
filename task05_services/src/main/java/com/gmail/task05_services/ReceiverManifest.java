package com.gmail.task05_services;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiverManifest extends BroadcastReceiver {

    public final static String MY_BROADCAST_MANIFEST_ACTION = "com.gmail.task05_services.ACTION_MANIFEST_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (MY_BROADCAST_MANIFEST_ACTION.equals(action)) {
            String text = intent.getStringExtra("TEXT");
            PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.KEY_PENDING_INTENT);
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(context, MyCustomService.class);
            intent1.putExtra("ACTION", action);
            intent1.putExtra(MainActivity.KEY_PENDING_INTENT, pendingIntent);
            context.startService(intent1);
        }
    }
}