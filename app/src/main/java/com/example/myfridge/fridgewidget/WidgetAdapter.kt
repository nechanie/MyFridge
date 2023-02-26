package com.example.myfridge.fridgewidget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.TextView
import com.example.myfridge.R


class WidgetAdapter:BaseAdapter() {
    private var itemList : MutableList<FoodItem> = mutableListOf()

    override fun getCount(): Int = itemList.size

    override fun getItem(p0: Int): FoodItem = itemList[p0]

    override fun getItemId(p0: Int): Long = itemList[p0].id

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        var newView: TextView
        if (convertView == null) {
            val temp: View = LayoutInflater.from(container!!.context).inflate(R.layout.fridge_widget, container, false);
            newView = temp.findViewById(R.id.widget_listView)
        }
        else{
            newView = convertView.findViewById(R.id.widget_listView)
        }
        val currentItem: FoodItem = getItem(position)
        newView.text = currentItem.name
        return newView;
    }
}

class ListService : RemoteViewsService(){
    lateinit var context: Context
    lateinit var intent: Intent

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ListRemoteViewsFactory(this.applicationContext, intent)
    }
}

class ListRemoteViewsFactory(
    private val context: Context,
    intent: Intent
) : RemoteViewsService.RemoteViewsFactory {

// See the RemoteViewsFactory API reference for the full list of methods to
// implement.
    private var itemList : MutableList<FoodItem> = mutableListOf()
    override fun onCreate() {
        itemList.add(FoodItem(1234141, "something1", 123))
        itemList.add(FoodItem(1234142, "something2", 123))
        itemList.add(FoodItem(1234143, "something3", 123))
        itemList.add(FoodItem(1234144, "something4", 123))
        itemList.add(FoodItem(1234145, "something5", 123))
        itemList.add(FoodItem(1234146, "something6", 123))
        itemList.add(FoodItem(1234147, "something7", 123))
        itemList.add(FoodItem(1234148, "something8", 123))
        itemList.add(FoodItem(12341429, "something9", 123))
    }

    override fun onDataSetChanged() {
        return
    }

    override fun onDestroy() {
        return
    }

    override fun getCount(): Int = itemList.size

    override fun getViewAt(position: Int): RemoteViews {
        return RemoteViews(context.packageName, R.layout.widget_item).apply {
            setTextViewText(R.id.widget_item_name, itemList[position].name)
            setTextViewText(R.id.widget_item_expr, itemList[position].expiration.toString())
            val fillInIntent = Intent().apply {
                Bundle().also { extras ->
                    extras.putInt(EXTRA_ITEM, position)
                    putExtras(extras)
                }
            }
            // Make it possible to distinguish the individual on-click
            // action of a given item.
            Log.d("getViewAt", "Was Executed")
            setOnClickFillInIntent(R.id.widget_item_name, fillInIntent)
        }
    }

    override fun getLoadingView(): RemoteViews {
        return RemoteViews(context.packageName, R.layout.widget_item)
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return itemList[position].id
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}