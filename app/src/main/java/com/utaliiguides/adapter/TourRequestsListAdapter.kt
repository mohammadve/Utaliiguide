package com.utaliiguides.adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utaliiguides.R
import com.utaliiguides.activity.TourRequestListDetailActivity
import com.utaliiguides.callBack.TourRequestListCallBack
import com.utaliiguides.models.TourRequestListModel
import kotlinx.android.synthetic.main.activity_tour_request_list_detail.*
import java.text.SimpleDateFormat
import java.text.ParseException


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
            holder.iv_requesterProfileImage

            Glide
                .with(mContext)
                .load(userList.profile_img)
                .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                .into(holder.iv_requesterProfileImage)

        }

        var strArrivalDate = tourList.tourStartdate
        var strDeptDate = tourList.tourEnddate


        try {
            //create SimpleDateFormat object with source string date format
            val sdfSource = SimpleDateFormat("yyyy-MM-dd")
            //parse the string into Date object
            val date = sdfSource.parse(strArrivalDate)
            //create SimpleDateFormat object with desired date format
            val sdfDestination = SimpleDateFormat("dd MMM, yyyy")
            //parse the date into another format
            strArrivalDate = sdfDestination.format(date)
        } catch (pe: ParseException) {
            println("Parse Exception : $pe")
        }


        try {
            val sdfSource = SimpleDateFormat("yyyy-MM-dd")
            val date = sdfSource.parse(strDeptDate)
            val sdfDestination = SimpleDateFormat("dd MMM, yyyy")
            strDeptDate = sdfDestination.format(date)
        } catch (pe: ParseException) {
            println("Parse Exception : $pe")
        }


        holder.tv_arrivalDateText.text = strArrivalDate
        holder.tv_departureDateText.text = strDeptDate
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
        var iv_requesterProfileImage : ImageView

        init {
            tv_requesterName = itemView.findViewById(R.id.tv_requesterName)
            tv_arrivalDateText = itemView.findViewById(R.id.tv_arrivalDateText)
            tv_departureDateText = itemView.findViewById(R.id.tv_departureDateText)
            tv_tripPrice = itemView.findViewById(R.id.tv_tripPrice)
            tv_tripType = itemView.findViewById(R.id.tv_tripType)
            tv_acceptText = itemView.findViewById(R.id.tv_acceptText)
            tv_rejectText = itemView.findViewById(R.id.tv_rejectText)
            cl_main = itemView.findViewById(R.id.cl_main)
            iv_requesterProfileImage = itemView.findViewById(R.id.iv_requesterProfileImage)

        }

        fun bindNotificationView(){

        }
    }
}