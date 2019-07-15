package com.utaliiguides.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.utaliiguides.R
import com.utaliiguides.activity.CreateNewTourActivity
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.activity.TripDetailActivity
import com.utaliiguides.adapter.DashboardListAdapter
import com.utaliiguides.helper.ConstantFragmentName
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*

class DashboardFragment : Fragment(), View.OnClickListener {

    private var mDashboardListAdapter: DashboardListAdapter? = null
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
    }
    private fun initView()
    {
        mDashboardListAdapter = DashboardListAdapter(activity!!)
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
