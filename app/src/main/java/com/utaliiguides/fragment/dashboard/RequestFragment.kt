package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.adapter.RequestsListAdapter
import com.utaliiguides.helper.ConstantFragmentName
import kotlinx.android.synthetic.main.fragment_request_list.*

class RequestFragment : Fragment() {

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
    }

    private fun initView()
    {
        requestsRecyclerView.setHasFixedSize(true)
        requestsRecyclerView.layoutManager = LinearLayoutManager(activity)
        requestsRecyclerView.adapter = RequestsListAdapter(activity!!)
    }


    private fun getTourRequestList() {

        

    }





}