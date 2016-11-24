package com.phone.home.sandbox;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Intent;
import android.widget.RelativeLayout.LayoutParams;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

//    NotificationCompat.Builder notification;
//    public static final int uniqueID = 5127635;

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        constructQuoteArray(list);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        notification = new NotificationCompat.Builder(this);
//        notification.setAutoCancel(true);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.content_scrolling);

//        TextView randQuote = new TextView (this);
//        randQuote.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//        randQuote.setText(randomQuote(list));
//        randQuote.setTextSize(20);
//
//        relativeLayout.addView(randQuote);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
//                Snackbar.make(view, randomQuote(list), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                String newQuote = randomQuote(list);
                //Send Notifcation
                //TODO: Service to automatically send notifications
//                notification.setSmallIcon(R.drawable.ic_notifications_black_24dp)
//                    .setTicker("New Quote: " + newQuote)
//                    .setWhen(System.currentTimeMillis())
//                    .setContentTitle("New Quote")
//                    .setContentText(newQuote);
//
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//                notification.setContentIntent(pendingIntent);
//
//                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                nm.notify(uniqueID, notification.build());

                setAlarm(view);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        } else if (id == R.id.action_quotes) {
            startActivity(new Intent(getApplicationContext(), QuoteList.class));
        }

        return super.onOptionsItemSelected(item);
    }

    void constructQuoteArray(ArrayList<String> list) {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.quotes));
        while(scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
    }

    String randomQuote(ArrayList<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }

    public void setAlarm(View view) {
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReciever.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 5127645, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }


}
