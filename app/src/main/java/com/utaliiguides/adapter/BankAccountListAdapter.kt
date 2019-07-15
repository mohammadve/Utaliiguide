package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R

class BankAccountListAdapter (var mContext : Context) : RecyclerView.Adapter<BankAccountListAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankAccountListAdapter.AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_bank_account_list,parent,false)
        return AccountViewHolder(view)
    }


    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: BankAccountListAdapter.AccountViewHolder, position: Int) {

        //holder.bindNotificationView()
    }

    class AccountViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
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