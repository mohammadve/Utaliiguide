package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.viewModel.AddBankAccountViewModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_add_bank_account.*

class AddBankAccountFragment : Fragment(),View.OnClickListener {

    var addBankAccountViewModel: AddBankAccountViewModel ?= null



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_bank_account, container, false)
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
        addBankAccountViewModel = ViewModelProviders.of(this).get(AddBankAccountViewModel::class.java)

        tv_cancle.setOnClickListener(this)
        btn_save.setOnClickListener(this)

    }



    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.tv_cancle->{

            }

            R.id.btn_save ->{
                if(et_accountHolderName.text.toString().equals("")){
                    Toast.makeText(activity,"Please enter the account holder name",Toast.LENGTH_SHORT).show()
                }
                else if(et_accountNumber.text.toString().equals("")){
                    Toast.makeText(activity,"Please enter the account number",Toast.LENGTH_SHORT).show()
                }
                else if(et_swiftCode.text.toString().equals("")){
                    Toast.makeText(activity,"Please enter the swift code",Toast.LENGTH_SHORT).show()
                }
                else if(et_branchCode.text.toString().equals("")){
                    Toast.makeText(activity,"Please enter the branch number",Toast.LENGTH_SHORT).show()
                }
                else {
                    addBankAccountDetails()
                }


            }


        }

    }



    private fun addBankAccountDetails() {

        addBankAccountViewModel!!.addBankAccount(activity!!,
            et_accountHolderName.text.toString(),
            et_accountNumber.text.toString(),
            et_swiftCode.text.toString(),
            et_branchCode.text.toString()).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){
                Utils.showToast(activity!!, it.get("message").asString)

            }
            else {
                Utils.showToast(activity!! , getString(R.string.msg_common_error))
            }


        })


    }




}