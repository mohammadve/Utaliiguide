package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R

class NotificationListAdapter (var mContext : Context) : RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListAdapter.NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_notification_list,parent,false)
        return NotificationViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: NotificationListAdapter.NotificationViewHolder, position: Int) {

        //holder.bindNotificationView()
    }

    class NotificationViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        //var tv_notification_title: TextView
        //var tv_notificationTime: TextView
        //var tv_notification_description: TextView

        init {
            //tv_notification_title = itemView.findViewById(R.id.tv_notification_title)
        }

        fun bindNotificationView(){

        }
    }
}