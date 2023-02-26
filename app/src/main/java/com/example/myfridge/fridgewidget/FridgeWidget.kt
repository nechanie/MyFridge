package com.example.myfridge.fridgewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.myfridge.MainActivity
import com.example.myfridge.R

/**
 * Implementation of App Widget functionality.
 */
const val TOAST_ACTION = "com.example.myfridge.TOAST_ACTION"
const val EXTRA_ITEM = "com.example.myfridge.EXTRA_ITEM"

class FridgeWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            // Set up the intent that starts the StackViewService, which
            // provides the views for this collection.
            val intent = Intent(context, ListService::class.java).apply {
                // Add the widget ID to the intent extras.
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }
            // Instantiate the RemoteViews object for the widget layout.
            val views = RemoteViews(context.packageName, R.layout.fridge_widget).apply {
                // Set up the RemoteViews object to use a RemoteViews adapter.
                // This adapter connects to a RemoteViewsService through the
                // specified intent.
                // This is how you populate the data.
                setRemoteAdapter(R.id.widget_listView, intent)

                // The empty view is displayed when the collection has no items.
                // It must be in the same layout used to instantiate the
                // RemoteViews object.
                setEmptyView(R.id.widget_listView, R.id.empty_view)
            }

            val toastPendingIntent: PendingIntent = Intent(
                context,
                MainActivity::class.java
            ).run {
                // Set the action for the intent.
                // When the user touches a particular view, it has the effect of
                // broadcasting TOAST_ACTION.
                action = TOAST_ACTION
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
                Log.d("PendingIntent", "Was Executed")
                PendingIntent.getActivity(context, 0, this, PendingIntent.FLAG_UPDATE_CURRENT)
            }
            Log.d("PendingIntent", "Was Executed2")
            views.setPendingIntentTemplate(R.id.widget_listView, toastPendingIntent)

            // Do additional processing specific to this widget.

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
