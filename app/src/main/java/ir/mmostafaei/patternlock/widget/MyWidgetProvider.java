package ir.mmostafaei.patternlock.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import java.util.Random;

import ir.mmostafaei.patternlock.R;
import ir.mmostafaei.patternlock.activity.ActivitySplash;
import ir.mmostafaei.patternlock.activity.OpenDoorActivity;

/***
 * Created by mohsen on 12/20/2016.
 */

public class MyWidgetProvider extends AppWidgetProvider {

  private static final String ACTION_CLICK = "ACTION_CLICK";

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    // Get all ids
    ComponentName thisWidget = new ComponentName(context,
      MyWidgetProvider.class);
    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    for (int widgetId : allWidgetIds) {


      RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
        R.layout.widget_layout);

//      // Register an onClickListener
//      Intent intent = new Intent(context, MyWidgetProvider.class);
//
//      intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
//
//      PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    //  remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
//      appWidgetManager.updateAppWidget(widgetId, remoteViews);

      Intent configIntent = new Intent(context, OpenDoorActivity.class);

      PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

      remoteViews.setOnClickPendingIntent(R.id.layout, configPendingIntent);
      appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }
  }
}
