package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R
import com.utalli.helpers.Utils

class DashboardListAdapter(var mContext : Context) : RecyclerView.Adapter<DashboardListAdapter.MyViewHolder>() {

    private var mClickListener: ListItemClickListener? = null

    interface ListItemClickListener {
        fun listItemClickListener(position: Int)
    }

    fun setItemClickListener(listener: ListItemClickListener) {
        this.mClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_dashboard_list,parent,false)
        return MyViewHolder(view)
    }



    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: DashboardListAdapter.MyViewHolder, position: Int) {

        holder.bindTripView(position)
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        //var tv_notification_title: TextView
        //var tv_notificationTime: TextView
        //var tv_notification_description: TextView

        init {
            //tv_notification_title = itemView.findViewById(R.id.tv_notification_title)
        }

        fun bindTripView(position: Int){
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    mClickListener!!.listItemClickListener(position)
                }
            })
        }
    }
}