package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.utaliiguides.R
import com.utaliiguides.callBack.PaymentRemoveBankDetailsCallBack
import com.utaliiguides.models.GetBankDetailsModel



class BankAccountListAdapter (var mContext : Context, var bankDetailsList: ArrayList<GetBankDetailsModel>, var itemListener: PaymentRemoveBankDetailsCallBack) : RecyclerView.Adapter<BankAccountListAdapter.AccountViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankAccountListAdapter.AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_bank_account_list,parent,false)
        return AccountViewHolder(view)
    }




    override fun getItemCount(): Int {
        return bankDetailsList.size
    }




    override fun onBindViewHolder(holder: BankAccountListAdapter.AccountViewHolder, position: Int) {

        var accountListItem = bankDetailsList.get(position)

        holder.tv_accountHolderName.text = accountListItem.account_name
        holder.tv_accountNumber.text = accountListItem.account_no
        holder.tv_swiftCode.text = accountListItem.account_swift_code
        holder.tv_branchNumber.text = accountListItem.branch_no

        if (accountListItem.account_defualt)
        {
            holder.radioBtn.isChecked = true
            holder.iv_deleteAccount.setColorFilter(mContext.getResources().getColor(R.color.grey));
            holder.iv_accountSetAsDefault.visibility = View.VISIBLE
            holder.tv_saveAsDefaultMsg.visibility = View.VISIBLE
        }
        else
        {
            holder.radioBtn.isChecked = false
            holder.iv_deleteAccount.setColorFilter(mContext.getResources().getColor(R.color.redColor));
            holder.iv_accountSetAsDefault.visibility = View.GONE
            holder.tv_saveAsDefaultMsg.visibility = View.GONE
        }
        
        holder.iv_deleteAccount.setOnClickListener {

            if (!accountListItem.account_defualt)
            {
                itemListener.removeBankDetails(position, "Delete")
            }
            else
            {

            }
        }
        
        holder.radioBtn.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked)
            {
                if(!accountListItem.account_defualt)
                {
                    itemListener.removeBankDetails(position, "Update")
                }
            }
        }

        //holder.bindNotificationView()
    }

    fun updateSetAsDefaultAccount(position: Int)
    {
        var i=0
        while(i<bankDetailsList.size){

            if(i==position){
                bankDetailsList.get(i).account_defualt=true
            }else{
                bankDetailsList.get(i).account_defualt=false
            }
            i++
        }
        notifyDataSetChanged()
    }



    class AccountViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var tv_accountHolderName : TextView
        var tv_accountNumber : TextView
        var tv_swiftCode : TextView
        var tv_branchNumber : TextView
        var iv_deleteAccount : ImageView
        var iv_accountSetAsDefault: ImageView
        var tv_saveAsDefaultMsg: TextView
        var radioBtn: RadioButton


        init {
            tv_accountHolderName = itemView.findViewById(R.id.tv_accountHolderName)
            tv_accountNumber = itemView.findViewById(R.id.tv_accountNumber)
            tv_swiftCode = itemView.findViewById(R.id.tv_swiftCode)
            tv_branchNumber = itemView.findViewById(R.id.tv_branchNumber)
            iv_deleteAccount = itemView.findViewById(R.id.iv_deleteAccount)

            iv_accountSetAsDefault = itemView.findViewById(R.id.iv_accountSetAsDefault)
            tv_saveAsDefaultMsg = itemView.findViewById(R.id.tv_saveAsDefaultMsg)
            radioBtn = itemView.findViewById(R.id.radioBtn)
        }

        fun bindNotificationView(){

        }

    }



}