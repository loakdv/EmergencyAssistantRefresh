/*
 *
 *  Created by Dmitry Garmyshev on 7/10/19 9:53 PM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 7/10/19 9:50 PM
 *
 */

package com.example.dmitriy.emergencyassistant.services;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.dmitriy.emergencyassistant.activities.based.ActivityMain;
import com.example.dmitriy.emergencyassistant.R;

/*
Класс с виджетом для экрана
 */
public class WidgetApp extends AppWidgetProvider {
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i : appWidgetIds) {
            clickWidget(context, appWidgetManager, i);
        }
    }



    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }





    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

    }




    private void clickWidget(Context ctx, AppWidgetManager appWidgetManager, int widgetID){

        RemoteViews widgetView = new RemoteViews(ctx.getPackageName(),
                R.layout.widget_app);

        // Конфигурационный экран (первая зона)
        Intent configIntent = new Intent(ctx, ActivityMain.class);
        configIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_CONFIGURE);
        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
        PendingIntent pIntent = PendingIntent.getActivity(ctx, widgetID,
                configIntent, 0);
        widgetView.setOnClickPendingIntent(R.id.widget_tv_sos, pIntent);


        // Обновляем виджет
        appWidgetManager.updateAppWidget(widgetID, widgetView);
    }



}
