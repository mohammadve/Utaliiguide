package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R

class PaymentAdapter (var mContext : Context) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentAdapter.PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_earning_payment,parent,false)
        return PaymentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: PaymentAdapter.PaymentViewHolder, position: Int) {

        //holder.bindNotificationView()
    }

    class PaymentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
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