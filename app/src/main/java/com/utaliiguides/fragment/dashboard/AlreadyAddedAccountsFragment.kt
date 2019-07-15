package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.adapter.BankAccountListAdapter
import com.utaliiguides.helper.ConstantFragmentName
import kotlinx.android.synthetic.main.fragment_already_added_account.*

class AlreadyAddedAccountsFragment : Fragment(), View.OnClickListener {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_already_added_account, container, false)
        updateToolbar()
        return view
    }

    private fun updateToolbar()
    {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.PAYMENT_SETTING_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(false)
        (activity as HomeActivity).setToolbarBackVisibility(true)
        (activity as HomeActivity).setToolbarNotificationVisibility(false)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)
    }

    private fun initView()
    {
        fabAddBankAccount.setOnClickListener(this)
        notificationRecyclerView.setHasFixedSize(true)
        notificationRecyclerView.layoutManager = LinearLayoutManager(activity)
        notificationRecyclerView.adapter = BankAccountListAdapter(activity!!)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.fabAddBankAccount ->
            {
                (activity as HomeActivity).setDisplayFragment(11)
            }
        }
    }
}