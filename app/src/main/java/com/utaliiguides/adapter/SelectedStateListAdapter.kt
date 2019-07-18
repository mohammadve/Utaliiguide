package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R
import com.utaliiguides.models.countryList.StateListData

class SelectedStateListAdapter (var mContext: Context) : RecyclerView.Adapter<SelectedStateListAdapter.MyViewHolder>() {

    var stateList = ArrayList<StateListData>()



    fun setStateList(newStateList: ArrayList<StateListData>, context: Context) {
        this.stateList = newStateList
        this.mContext = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedStateListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_selected_state, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
        return stateList.size
    }


    override fun onBindViewHolder(holder: SelectedStateListAdapter.MyViewHolder, position: Int) {

        var selectedStateItem = stateList.get(position)
        holder.tv_state_name.text = selectedStateItem.getName()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_state_name: TextView
        var layoutParent: LinearLayout
        var iv_icon: ImageView

        init {
            tv_state_name = itemView.findViewById(R.id.tv_state_name)
            layoutParent = itemView.findViewById(R.id.layout_parent)
            iv_icon = itemView.findViewById(R.id.iv_icon)
        }

        fun bind() {
//            if (stateList.get(position).isSelected) {
//                layoutParent.setBackgroundResource(R.drawable.selected_round_rect)
//                iv_icon.setBackgroundResource(R.drawable.close_icon)
//
//            } else {
//                layoutParent.setBackgroundResource(R.drawable.unselected_round_rect)
//                iv_icon.setBackgroundResource(R.drawable.ic_report_black_24dp)
//
//            }
//
//
//            layoutParent.setOnClickListener {
//                stateList.get(position).isSelected = !stateList.get(position).isSelected
//                itemListener?.recyclerViewListClicked(stateList.get(position))
//                notifyItemChanged(position)
//                //stateListAdd.remove(stateListAdd.get(position))
//            }
        }
    }
}