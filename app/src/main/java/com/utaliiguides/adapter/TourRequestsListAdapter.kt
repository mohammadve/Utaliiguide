package com.utaliiguides.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R
import com.utaliiguides.activity.TourRequestListDetailActivity
import com.utaliiguides.callBack.TourRequestListCallBack
import com.utaliiguides.models.TourRequestListModel
import java.text.SimpleDateFormat


class TourRequestsListAdapter (var mContext : Context, var tourRequestList : ArrayList<TourRequestListModel>, var itemListener : TourRequestListCallBack) : RecyclerView.Adapter<TourRequestsListAdapter.MyRequestViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourRequestsListAdapter.MyRequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_request_list,parent,false)
        return MyRequestViewHolder(view)
    }



    override fun getItemCount(): Int {
        return tourRequestList.size
    }

    override fun onBindViewHolder(holder: TourRequestsListAdapter.MyRequestViewHolder, position: Int) {

       // var requestList = tourRequestList.get(position)



        var tourList = tourRequestList.get(position).tour

        if(tourList.user != null){
            var userList = tourList.user

            holder.tv_requesterName.text = userList.u_name
        }



        val arrivalDate = tourList.tourStartdate
        var sdf1 = SimpleDateFormat("YYYY-MM-dd")
        val newArrivalDate = sdf1.parse(arrivalDate)
        sdf1 = SimpleDateFormat("dd, MMM yyyy")
        val newArrivalDateString = sdf1.format(newArrivalDate)


        val depDate = tourList.tourEnddate
        var spf2 = SimpleDateFormat("YYYY-MM-dd")
        val newDepDate = spf2.parse(depDate)
        spf2 = SimpleDateFormat("dd, MMM yyyy")
        val newDepDateString = spf2.format(newDepDate)



        holder.tv_arrivalDateText.text = newArrivalDateString
        holder.tv_departureDateText.text = newDepDateString
        holder.tv_tripPrice.text = "\$ "+tourList.tourcost

        if(tourList.tourtype == 1){
            holder.tv_tripType.text = "Private"
        }
        else if(tourList.tourtype == 2){
            holder.tv_tripType.text = "Pool"
        }


        holder.cl_main.setOnClickListener {
            var intent = Intent(mContext, TourRequestListDetailActivity::class.java)
            intent.putExtra("tour_req_id",tourList.tourReqId)
            mContext.startActivity(intent)
        }


        holder.tv_acceptText.setOnClickListener {

            itemListener.acceptRejectRequestListener(position, "Accept")
        }


        holder.tv_rejectText.setOnClickListener {
            itemListener.acceptRejectRequestListener(position, "Delete")
        }




        //holder.bindNotificationView()
    }

    class MyRequestViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_requesterName: TextView
        var tv_arrivalDateText: TextView
        var tv_departureDateText: TextView
        var tv_tripPrice : TextView
        var tv_tripType : TextView
        var tv_acceptText : TextView
        var tv_rejectText : TextView
        var cl_main : ConstraintLayout

        init {
            tv_requesterName = itemView.findViewById(R.id.tv_requesterName)
            tv_arrivalDateText = itemView.findViewById(R.id.tv_arrivalDateText)
            tv_departureDateText = itemView.findViewById(R.id.tv_departureDateText)
            tv_tripPrice = itemView.findViewById(R.id.tv_tripPrice)
            tv_tripType = itemView.findViewById(R.id.tv_tripType)
            tv_acceptText = itemView.findViewById(R.id.tv_acceptText)
            tv_rejectText = itemView.findViewById(R.id.tv_rejectText)
            cl_main = itemView.findViewById(R.id.cl_main)

        }

        fun bindNotificationView(){

        }
    }
}