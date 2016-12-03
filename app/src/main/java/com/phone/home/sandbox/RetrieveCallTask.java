package com.phone.home.sandbox;

import android.os.AsyncTask;

/**
 * Created by jetpackcat on 12/3/2016.
 */

public class RetrieveCallTask extends AsyncTask<TaskParams, Void, TaskParams> {

    protected TaskParams doInBackground(TaskParams... params) {
        try {
            ApiCaller apiCaller = new ApiCaller(params[0].str);
            String result = apiCaller.getString();
            return new TaskParams(result, params[0].context);
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }
    }

    protected void onPostExecute(TaskParams result) {
        AlarmUtil alarmUtil = new AlarmUtil(result.context);
        alarmUtil.setAlarm(result.str);
    }


}
