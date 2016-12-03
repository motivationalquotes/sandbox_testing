package com.phone.home.sandbox;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final Boolean notify = sharedPref.getBoolean("notify", false);

        final TaskParams params = new TaskParams("https://motivationalquotes.herokuapp.com/quote", this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

                new RetrieveCallTask().execute(params);

                //Send Notifcation
                //TODO: Service to automatically send notifications

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

//    void constructQuoteArray(ArrayList<String> list) {
//        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.quotes));
//        while(scanner.hasNextLine()) {
//            list.add(scanner.nextLine());
//        }
//    }

//    String randomQuote(ArrayList<String> list) {
//        return list.get((int) (Math.random() * list.size()));
//    }

    public void setAlarm(String myQuote) {
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlertReceiver.class);
        alertIntent.putExtra("QUOTE", myQuote);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, (int) (Math.random() * Integer.MAX_VALUE), alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }


}
