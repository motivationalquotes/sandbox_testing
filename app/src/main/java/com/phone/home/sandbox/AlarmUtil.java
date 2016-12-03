package com.phone.home.sandbox;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.GregorianCalendar;

/**
 * Created by jetpackcat on 12/3/2016.
 */

public class AlarmUtil {

    Context context;

    public AlarmUtil(Context myContext) {
        context = myContext;
    }

    public void setAlarm(String myQuote) {
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(context, AlertReceiver.class);
        alertIntent.putExtra("QUOTE", myQuote);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(context, (int) (Math.random() * Integer.MAX_VALUE), alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
