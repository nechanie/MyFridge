package com.example.myfridge.ui.home

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.database.FridgeItemInfo
import com.example.myfridge.data.fridge.FridgeContent
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var homeList: List<FridgeItemInfo> = listOf()

    override fun getItemCount(): Int = homeList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.homeList[position])
    }

    fun updateHomeList(contents: FridgeContent?){
        homeList = contents?.items ?: listOf()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val itemIMG: ImageView = view.findViewById(R.id.home_item_img)
        private val itemName: TextView = view.findViewById(R.id.home_item_name)
        private val itemExp: TextView = view.findViewById(R.id.home_item_expr)

        private lateinit var currentItemInfo: FridgeItemInfo

        fun bind(listItem: FridgeItemInfo){
            currentItemInfo = listItem

//            val imgUri: Uri = Uri.parse(listItem.img.toString())
//            itemIMG.setImageURI(imgUri)
            val bytes: ByteArray = listItem.img
            val newBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, BitmapFactory.Options())
            itemIMG.setImageBitmap(newBitmap)
            itemName.text = listItem.name
            itemExp.text = SimpleDateFormat("MM/dd/yyyy").format(Date(listItem.exp))
        }
    }
}