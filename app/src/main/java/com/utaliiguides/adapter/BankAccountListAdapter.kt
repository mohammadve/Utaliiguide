package com.utaliiguides.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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

        holder.tv_accountHolderName.text = bankDetailsList.get(position).account_name
        holder.tv_accountNumber.text = bankDetailsList.get(position).account_no
        holder.tv_swiftCode.text = bankDetailsList.get(position).account_swift_code
        holder.tv_branchNumber.text = bankDetailsList.get(position).branch_no



        holder.iv_deleteAccount.setOnClickListener {

            itemListener.removeBankDetails(bankDetailsList.get(position))

        }


        //holder.bindNotificationView()
    }



    class AccountViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var tv_accountHolderName : TextView
        var tv_accountNumber : TextView
        var tv_swiftCode : TextView
        var tv_branchNumber : TextView
        var iv_deleteAccount : ImageView


        init {
            tv_accountHolderName = itemView.findViewById(R.id.tv_accountHolderName)
            tv_accountNumber = itemView.findViewById(R.id.tv_accountNumber)
            tv_swiftCode = itemView.findViewById(R.id.tv_swiftCode)
            tv_branchNumber = itemView.findViewById(R.id.tv_branchNumber)
            iv_deleteAccount = itemView.findViewById(R.id.iv_deleteAccount)
        }

        fun bindNotificationView(){

        }

    }



}