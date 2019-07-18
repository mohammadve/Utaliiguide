package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.utaliiguides.R
import com.utaliiguides.RobotoRegularTextView
import com.utaliiguides.models.countryList.CountryListData

class CountryListAdapter(var mContext: Context, var mCountryList: ArrayList<CountryListData>): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getCount(): Int {
        return mCountryList.size
    }

    override fun getItem(p0: Int): Any? {
        return mCountryList.get(p0).getName()
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_custom_spinner, parent, false)
            holder = ViewHolder()
            holder.tv_spinnerText = convertView!!.findViewById<View>(R.id.tv_spinnerTextNew) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.tv_spinnerText!!.text = mCountryList[position].getName()
        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_row_spinner_drop_down_window, parent, false)
            holder = ViewHolder()

            holder.tv_spinnerDropdownText =
                convertView!!.findViewById<View>(R.id.tv_spinnerDropdownText_new) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.tv_spinnerDropdownText!!.text = mCountryList[position].getName()
        return convertView
    }

    internal class ViewHolder {
        var tv_spinnerText: TextView? = null
        var tv_spinnerDropdownText: TextView? = null
    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View
//        val mViewHolder: ItemRowViewHolder
//        if (convertView == null) {
//            view = mInflater.inflate(R.layout.item_custom_spinner, parent, false)
//            mViewHolder = ItemRowViewHolder(view)
//            view?.tag = mViewHolder
//        } else {
//            view = convertView
//            mViewHolder = view.tag as ItemRowViewHolder
//        }
//         //setting adapter item height programatically.
//
////        val params = view.layoutParams
////        params.height = 60
////        view.layoutParams = params
//
//        mViewHolder.spinnerItemText.text = mCountryList.get(position).getName()
//        return view
//    }

//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View
//        val mViewHolder: ItemRowViewHolder
//        if (convertView == null) {
//            view = mInflater.inflate(R.layout.item_row_spinner_drop_down_window, parent, false)
//            mViewHolder = ItemRowViewHolder(view)
//            view?.tag = mViewHolder
//        } else {
//            view = convertView
//            mViewHolder = view.tag as ItemRowViewHolder
//        }
//        // setting adapter item height programatically.
//
//        val params = view.layoutParams
//        params.height = 60
//        view.layoutParams = params
//
//        mViewHolder.dropDownWindowText.text = mCountryList.get(position).getName()
//        return view
//    }

//    inner class ItemRowViewHolder(row: View) {
//
//        var spinnerItemText: RobotoRegularTextView
//        //var dropDownWindowText: RobotoRegularTextView
//
//        init {
//            spinnerItemText = row.findViewById(R.id.tv_spinnerTextNew)
//            //dropDownWindowText = row.findViewById(R.id.tv_spinnerDropdownText_new)
//        }
//    }
}