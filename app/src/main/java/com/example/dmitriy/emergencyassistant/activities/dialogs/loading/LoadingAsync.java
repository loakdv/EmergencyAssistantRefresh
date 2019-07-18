/*
 *
 *  Created by Dmitry Garmyshev on 7/18/19 12:50 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/17/19 6:01 PM
 *
 */

package com.example.dmitriy.emergencyassistant.activities.dialogs.loading;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityVolunteer;

public class LoadingAsync extends AsyncTask<Void, Void, Void> {

    Activity activity;

    public LoadingAsync(Activity activityVolunteer){
        this.activity = activityVolunteer;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();}

    @Override
    protected Void doInBackground(Void... voids) {
        Intent i = new Intent(activity, ActivityDialogLoading.class);
        activity.startActivity(i);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

}
