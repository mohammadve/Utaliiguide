package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.adapter.PaymentAdapter
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.models.PaymentListModel
import com.utaliiguides.models.TourRequestListModel
import com.utaliiguides.viewModel.PaymentViewModel
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment() {
    var paymentViewModel : PaymentViewModel ?= null
    lateinit var paymentList : ArrayList<PaymentListModel>
    var paymentAdapter : PaymentAdapter?= null




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)
        updateToolbar()

        getPaymentListRequest()

        return view
    }


    private fun updateToolbar() {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.PAYMENT_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(true)
        (activity as HomeActivity).setToolbarBackVisibility(false)
        (activity as HomeActivity).setToolbarNotificationVisibility(false)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(true)

        paymentViewModel = ViewModelProviders.of(this).get(PaymentViewModel::class.java)
    }


    private fun initView() {

        paymentList = ArrayList<PaymentListModel>()
        paymentAdapter = PaymentAdapter(activity!!, paymentList)

        receivedPaymentRV.setHasFixedSize(true)
        receivedPaymentRV.layoutManager = LinearLayoutManager(activity)
        receivedPaymentRV.adapter = paymentAdapter
    }



    private fun getPaymentListRequest() {

        paymentViewModel!!.getPayment_totalEarnings(activity!!).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("Earning") && it.get("Earning") is JsonObject) {

                    var dataObject =  it.getAsJsonObject("Earning")

                    if(dataObject.has("totalearning") && !dataObject.get("totalearning").isJsonNull){

                        tv_totalEarningAmount.setText("" + dataObject.get("totalearning"))
                    }

                }

                if(it.has("data") && it.get("data") is JsonArray){

                    val type = object : TypeToken<ArrayList<PaymentListModel>>() {}.type
                    var paymentListData = Gson().fromJson<ArrayList<PaymentListModel>>(it.get("data"), type)

                    if(paymentListData!= null && paymentListData.size>0){

                        paymentList.clear()
                        paymentList.addAll(paymentListData)
                        paymentAdapter!!.notifyDataSetChanged()

                    }

                    if(paymentList.size == 0){
                        cl_noNotificationFound.visibility = View.VISIBLE
                        receivedPaymentRV.visibility = View.GONE
                    } else{
                        cl_noNotificationFound.visibility = View.GONE
                        receivedPaymentRV.visibility = View.VISIBLE
                    }






                }


            }


        })

    }



}