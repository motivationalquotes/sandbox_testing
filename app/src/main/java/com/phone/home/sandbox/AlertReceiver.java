package com.phone.home.sandbox;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AlertReceiver extends BroadcastReceiver {

    private String quote;

    @Override
    public void onReceive(Context context, Intent intent) {
        quote = intent.getStringExtra("QUOTE");
        createNotification(context, quote);
    }

    private void createNotification(Context context, String quote) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder notification;
        final int uniqueID = (int) (Math.random() * Integer.MAX_VALUE);

        notification = new NotificationCompat.Builder(context);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String ringtone = sharedPref.getString("notification_ringtone", "");
        Boolean vibrate = sharedPref.getBoolean("notification_vibrate", true);

        notification.setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setTicker("New Quote: " + quote)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("New Quote")
                .setContentText(quote)
                .setAutoCancel(true)
                .setSound(Uri.parse(ringtone))
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_HIGH);

        if(vibrate)
            notification.setDefaults(NotificationCompat.DEFAULT_LIGHTS | NotificationCompat.DEFAULT_VIBRATE);
        else
            notification.setDefaults(NotificationCompat.DEFAULT_LIGHTS);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());
    }
}
