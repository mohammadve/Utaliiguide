package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utaliiguides.R
import com.utaliiguides.models.GuideTripListModel
import com.utalli.helpers.Utils
import de.hdodenhof.circleimageview.CircleImageView

class DashboardListAdapter(var mContext : Context, var guideTripsList : ArrayList<GuideTripListModel>) : RecyclerView.Adapter<DashboardListAdapter.MyViewHolder>() {

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
        return guideTripsList.size
    }

    override fun onBindViewHolder(holder: DashboardListAdapter.MyViewHolder, position: Int) {

        var guideTripItem = guideTripsList.get(position)

        holder.tv_dateText.text = guideTripItem.tourStartdate + " - "+ guideTripItem.tourEnddate
       // holder.tv_poolUserCountText.text =  guideTripItem.no_of_members + " Tourist in this Pool"


        if(guideTripItem.tourtype == 1){

            holder.tv_tripTypeText.setText("Private")
            holder.iv_messageUserProfileImage.visibility = View.VISIBLE
            holder.iv_messageUserProfileImage
            Glide.with(mContext)
                .load(guideTripItem.user.profile_img)
                .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                .into(holder.iv_messageUserProfileImage)
            holder.tv_poolUserCountText.setText(guideTripItem.user.u_name)

        }
        else if(guideTripItem.tourtype == 2){
            holder.tv_tripTypeText.setText("Pool")
            holder.tv_poolUserCountText.setText(guideTripItem.no_of_members + " Tourist in this Pool")
            holder.iv_messageUserProfileImage.visibility = View.GONE
        }


        holder.bindTripView(position)



    }




    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_dateText: TextView
        var tv_poolUserCountText: TextView
        var tv_tripTypeText : TextView
        var iv_messageUserProfileImage : CircleImageView


        init {
            tv_dateText = itemView.findViewById(R.id.tv_dateText)
            tv_poolUserCountText = itemView.findViewById(R.id.tv_poolUserCountText)
            tv_tripTypeText = itemView.findViewById(R.id.tv_tripTypeText)
            iv_messageUserProfileImage = itemView.findViewById(R.id.iv_messageUserProfileImage)
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