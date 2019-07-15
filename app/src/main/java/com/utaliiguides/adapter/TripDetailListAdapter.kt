package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R

class TripDetailListAdapter (var mContext : Context) : RecyclerView.Adapter<TripDetailListAdapter.MyTripDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripDetailListAdapter.MyTripDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_trip_detail_list,parent,false)
        return MyTripDetailViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: TripDetailListAdapter.MyTripDetailViewHolder, position: Int) {

        //holder.bindNotificationView()
    }

    class MyTripDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
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