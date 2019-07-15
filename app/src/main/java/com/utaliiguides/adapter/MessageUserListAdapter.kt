package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R

class MessageUserListAdapter (var mContext : Context) : RecyclerView.Adapter<MessageUserListAdapter.MyMessageUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageUserListAdapter.MyMessageUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_message_user_list,parent,false)
        return MyMessageUserViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MessageUserListAdapter.MyMessageUserViewHolder, position: Int) {

        //holder.bindNotificationView()
    }

    class MyMessageUserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
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