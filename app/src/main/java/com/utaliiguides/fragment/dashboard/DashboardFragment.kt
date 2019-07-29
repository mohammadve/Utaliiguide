package com.utaliiguides.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.utaliiguides.R
import com.utaliiguides.activity.CreateNewTourActivity
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.activity.TripDetailActivity
import com.utaliiguides.adapter.DashboardListAdapter
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.models.GuideTripListModel
import com.utaliiguides.viewModel.DashBoardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment(), View.OnClickListener {

    private var mDashboardListAdapter: DashboardListAdapter? = null
    var newRequestCount: Int = 0

    var dashBoardViewModel : DashBoardViewModel ?= null

    lateinit var guideTripsList: ArrayList<GuideTripListModel>




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setupClickListener()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        updateToolbar()

        return view
    }

    private fun updateToolbar()
    {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.DASHBOARD_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(true)
        (activity as HomeActivity).setToolbarBackVisibility(false)
        (activity as HomeActivity).setToolbarNotificationVisibility(true)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)

        dashBoardViewModel = ViewModelProviders.of(this).get(DashBoardViewModel::class.java)

        getGuideTripListRequest()

        newRequestCount = (activity as HomeActivity).getNewRequestCount()
    }




    private fun getGuideTripListRequest() {

        dashBoardViewModel!!.getGuideTripList(activity!!).observe(this, androidx.lifecycle.Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data") &&  it.get("data") is JsonArray){

                    val type = object : TypeToken<List<GuideTripListModel>>() {}.type
                    var tripsList = Gson().fromJson<List<GuideTripListModel>>(it.get("data"), type)


                    if(tripsList != null && tripsList.size > 0){

                         guideTripsList.clear()
                        guideTripsList.addAll(tripsList)
                        mDashboardListAdapter!!.notifyDataSetChanged()

                    }
                }

            }

        })

    }



    private fun initView()
    {
        if (newRequestCount != 0)
        {
            cardViewRequests.visibility = View.VISIBLE
            tv_newRequestCount.text = newRequestCount.toString()
        }
        else
        {
            cardViewRequests.visibility = View.GONE
        }

        guideTripsList = ArrayList<GuideTripListModel>()

        mDashboardListAdapter = DashboardListAdapter(activity!!,guideTripsList)
        tripRecyclerView.setHasFixedSize(true)
        tripRecyclerView.layoutManager = LinearLayoutManager(activity)
        tripRecyclerView.adapter = mDashboardListAdapter
        mDashboardListAdapter!!.setItemClickListener(object : DashboardListAdapter.ListItemClickListener {
            override
            fun listItemClickListener(position: Int) {
                var intent = Intent(activity!!, TripDetailActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun setupClickListener()
    {
        cardViewRequests.setOnClickListener(this)
        fabCreateNewTour.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.cardViewRequests ->
            {
                (activity as HomeActivity).setDisplayFragment(8)
            }
            R.id.fabCreateNewTour ->
            {
                var intent = Intent(activity!!, CreateNewTourActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
