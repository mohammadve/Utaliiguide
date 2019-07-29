package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.adapter.TourRequestsListAdapter
import com.utaliiguides.callBack.TourRequestListCallBack
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.models.GetBankDetailsModel
import com.utaliiguides.models.TourRequestListModel
import com.utaliiguides.viewModel.TourRequestViewModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_request_list.*

class TourRequestFragment : Fragment(), TourRequestListCallBack{

    var tourRequestViewModel : TourRequestViewModel ?= null
    lateinit var tourRequestList : ArrayList<TourRequestListModel>
    var tourRequestsListAdapter : TourRequestsListAdapter ?=null





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_request_list, container, false)
        updateToolbar()

        getTourRequestList()

        return view
    }



    private fun updateToolbar()
    {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.REQUESTS_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(false)
        (activity as HomeActivity).setToolbarBackVisibility(true)
        (activity as HomeActivity).setToolbarNotificationVisibility(false)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)

        tourRequestViewModel = ViewModelProviders.of(this).get(TourRequestViewModel::class.java)
    }

    private fun initView()
    {
        tourRequestList = ArrayList<TourRequestListModel>()
        tourRequestsListAdapter = TourRequestsListAdapter(activity!!,tourRequestList, this)


        requestsRecyclerView.setHasFixedSize(true)
        requestsRecyclerView.layoutManager = LinearLayoutManager(activity)
        requestsRecyclerView.adapter = tourRequestsListAdapter
    }


    private fun getTourRequestList() {

        tourRequestViewModel!!.getTourRequest(activity!!).observe(this , Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data") && it.get("data") is JsonArray){

                    val type = object : TypeToken<ArrayList<TourRequestListModel>>() {}.type
                    var requestList = Gson().fromJson<ArrayList<TourRequestListModel>>(it.get("data"), type)



                    if(requestList!= null && requestList.size > 0){
                        tourRequestList.clear()
                         tourRequestList.addAll(requestList)
                        tourRequestsListAdapter?.notifyDataSetChanged()
                    }


                    if(tourRequestList.size == 0){
                        cl_no_recentTourFound.visibility = View.VISIBLE
                        requestsRecyclerView.visibility = View.GONE
                    } else{
                        cl_no_recentTourFound.visibility = View.GONE
                        requestsRecyclerView.visibility = View.VISIBLE
                    }



                }
            }
        })

    }


    override fun acceptRejectRequestListener(position: Int, message: String) {
        var selectedItem = tourRequestList.get(position)

        if(message.equals("Accept")){
            acceptNewTourRequest(selectedItem)
        }
        else if(message.equals("Delete")){
            rejectNewTourRequest(selectedItem)
        }

    }




    private fun rejectNewTourRequest(selectedItem: TourRequestListModel) {

        tourRequestViewModel!!.rejectNewTourRequest(activity!!, selectedItem.id, 3).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                //Utils.showToast(activity!!, it.get("message").asString)
                Utils.showToast(activity!!, "Tour request rejected")

                tourRequestList.remove(selectedItem)
                tourRequestsListAdapter!!.notifyDataSetChanged()

                if(tourRequestList.size == 0){
                    cl_no_recentTourFound.visibility = View.VISIBLE
                    requestsRecyclerView.visibility = View.GONE
                } else{
                    cl_no_recentTourFound.visibility = View.GONE
                    requestsRecyclerView.visibility = View.VISIBLE
                }

            }
        })
    }


    private fun acceptNewTourRequest(selectedItem: TourRequestListModel) {

        tourRequestViewModel!!.acceptNewTourRequest(activity!!, selectedItem.id, 2).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

               // Utils.showToast(activity!!, it.get("message").asString)
                Utils.showToast(activity!!, "Tour request accepted")

                tourRequestList.remove(selectedItem)
                tourRequestsListAdapter!!.notifyDataSetChanged()

                if(tourRequestList.size == 0){
                    cl_no_recentTourFound.visibility = View.VISIBLE
                    requestsRecyclerView.visibility = View.GONE
                } else{
                    cl_no_recentTourFound.visibility = View.GONE
                    requestsRecyclerView.visibility = View.VISIBLE
                }

            }

        })
    }






}