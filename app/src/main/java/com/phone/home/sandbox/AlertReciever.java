package com.phone.home.sandbox;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class AlertReciever extends BroadcastReceiver {
    public AlertReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        createNotification(context, "Times Up", "5 seconds has passed", "Alert");
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder notification;
        final int uniqueID = 5127645;

        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);

        notification.setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setTicker(msgAlert)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(msg)
                .setContentText(msgText);

        notification.setContentIntent(pendingIntent);

        notification.setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(uniqueID, notification.build());
    }
}
