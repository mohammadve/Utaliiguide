package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R

class RequestsListAdapter (var mContext : Context) : RecyclerView.Adapter<RequestsListAdapter.MyRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsListAdapter.MyRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_request_list,parent,false)
        return MyRequestViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: RequestsListAdapter.MyRequestViewHolder, position: Int) {

        //holder.bindNotificationView()
    }

    class MyRequestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
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