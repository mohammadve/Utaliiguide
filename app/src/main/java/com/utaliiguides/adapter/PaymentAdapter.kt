package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R
import com.utaliiguides.models.PaymentListModel
import de.hdodenhof.circleimageview.CircleImageView

class PaymentAdapter (var mContext : Context, var paymentList: ArrayList<PaymentListModel>) : RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentAdapter.PaymentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_earning_pool_private_payment,parent,false)
        return PaymentViewHolder(view)
    }



    override fun getItemCount(): Int {
        return paymentList.size
    }


    override fun onBindViewHolder(holder: PaymentAdapter.PaymentViewHolder, position: Int) {

        //holder.bindNotificationView()
    }


    class PaymentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_dateText: TextView
        var tv_poolType: TextView
        var iv_messageUserProfileImage: CircleImageView
        var tv_poolDetail : TextView
        var tv_amount : TextView
        var tv_dayNameText : TextView

        init {
            tv_dateText = itemView.findViewById(R.id.tv_dateText)
            tv_poolType = itemView.findViewById(R.id.tv_poolType)
            iv_messageUserProfileImage = itemView.findViewById(R.id.iv_messageUserProfileImage)
            tv_poolDetail = itemView.findViewById(R.id.tv_poolDetail)
            tv_amount = itemView.findViewById(R.id.tv_amount)
            tv_dayNameText = itemView.findViewById(R.id.tv_dayNameText)

        }

        fun bindNotificationView(){

        }
    }


}