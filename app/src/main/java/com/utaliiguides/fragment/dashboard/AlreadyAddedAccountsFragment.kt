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
import com.google.gson.reflect.TypeToken
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.adapter.BankAccountListAdapter
import com.utaliiguides.callBack.PaymentRemoveBankDetailsCallBack
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.models.GetBankDetailsModel
import com.utaliiguides.viewModel.AlreadyAddedAccountsViewModel
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_already_added_account.*

class AlreadyAddedAccountsFragment : Fragment(), View.OnClickListener {

    var alreadyAddedAccountsViewModel : AlreadyAddedAccountsViewModel?= null
    var bankAccountListAdapter : BankAccountListAdapter ?=null
    var bankDetailsList = ArrayList<GetBankDetailsModel>()



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_already_added_account, container, false)
        updateToolbar()
        return view
    }


    private fun updateToolbar() {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.PAYMENT_SETTING_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(false)
        (activity as HomeActivity).setToolbarBackVisibility(true)
        (activity as HomeActivity).setToolbarNotificationVisibility(false)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)
    }


    private fun initView() {
        alreadyAddedAccountsViewModel = ViewModelProviders.of(this).get(AlreadyAddedAccountsViewModel::class.java)

        fabAddBankAccount.setOnClickListener(this)
        rv_getBankDetails_list.setHasFixedSize(true)
        rv_getBankDetails_list.layoutManager = LinearLayoutManager(activity)

        getBankDetailsList()
    }




    private fun getBankDetailsList() {

        alreadyAddedAccountsViewModel!!.alreadyAddedAccounts(activity!!).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data") && it.get("data") is JsonArray){

                    val type = object : TypeToken<ArrayList<GetBankDetailsModel>>() {}.type
                    bankDetailsList = Gson().fromJson<ArrayList<GetBankDetailsModel>>(it.get("data"), type)

                    if(bankDetailsList != null && bankDetailsList.size >0){

                        bankAccountListAdapter = BankAccountListAdapter(activity!!, bankDetailsList, object : PaymentRemoveBankDetailsCallBack{

                            override fun removeBankDetails(itemDetails: GetBankDetailsModel) {
                                removeBankDetailsRequest(itemDetails.id, itemDetails.guideId, itemDetails)
                            }
                        })

                        rv_getBankDetails_list.adapter = bankAccountListAdapter

                    }

                }


            }


        })


    }


    private fun removeBankDetailsRequest(accountId: Int, guideId: Int, itemDetails: GetBankDetailsModel) {

        alreadyAddedAccountsViewModel!!.removeBankDetails(activity!!, accountId, guideId).observe(this, Observer {

            if(it != null && it.has("status") && it.get("status").asString.equals("1")){
                Utils.showToast(activity!! , it.get("message").asString)

                bankDetailsList.remove(itemDetails)
                bankAccountListAdapter!!.notifyDataSetChanged()

            }


        })

    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fabAddBankAccount -> {
                (activity as HomeActivity).setDisplayFragment(11)
            }
        }
    }


}