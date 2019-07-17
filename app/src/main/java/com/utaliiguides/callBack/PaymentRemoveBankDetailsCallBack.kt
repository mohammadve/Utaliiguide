package com.utaliiguides.callBack

import com.utaliiguides.models.GetBankDetailsModel

interface PaymentRemoveBankDetailsCallBack {


    fun removeBankDetails(position : Int, message: String)
}