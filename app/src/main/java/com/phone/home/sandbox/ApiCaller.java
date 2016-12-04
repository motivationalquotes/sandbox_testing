package com.phone.home.sandbox;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.GregorianCalendar;

/**
 * Created by jetpackcat on 12/3/2016.
 */

public class ApiCaller {

    Context context;
    RequestQueue queue;
    String url;
    String result = "";

    public ApiCaller(Context newContext, String newUrl) {
        context = newContext;
        queue = Volley.newRequestQueue(context);
        url = newUrl;
    }

    public void setUrl(String newUrl) {
        url = newUrl;
    }

    public void get() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Long alertTime = new GregorianCalendar().getTimeInMillis();

                        Intent alertIntent = new Intent(context, AlertReceiver.class);
                        alertIntent.putExtra("QUOTE", response);

                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(context, (int) (Math.random() * Integer.MAX_VALUE), alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result = "Error: Quote not found.";
            }
        });

        queue.add(stringRequest);

//        return result;
    }

}
