package com.utaliiguides.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utaliiguides.R
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import com.utalli.network.ApiClient
import com.utalli.network.ApiService
import retrofit2.Call
import retrofit2.Response

class AlreadyAddedAccountsViewModel : ViewModel(){

    var alreadyAddedAccountsResult : MutableLiveData<JsonObject> ?= null
    var removeBankDetailsResult : MutableLiveData<JsonObject> ?= null
    var preference : AppPreference?= null



    fun alreadyAddedAccounts(mContext : Context) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()

        alreadyAddedAccountsResult = MutableLiveData()


        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.alreadyAddedAccounts(token, guideId)


        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body() != null){
                    alreadyAddedAccountsResult!!.value = response.body()
                }
                else{
                    Utils.showToast(mContext, mContext.resources.getString(R.string.msg_common_error))
                }
            }
        })


        return alreadyAddedAccountsResult!!
    }


    fun removeBankDetails(mContext : Context, guideId: Int, accountId : Int) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        removeBankDetailsResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.removeBankDetails(token, guideId, accountId)

        call.enqueue(object: retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                   removeBankDetailsResult!!.value = response.body()
                }
                else{
                    Utils.showToast(mContext, mContext.resources.getString(R.string.msg_common_error))
                }
            }

        })


        return removeBankDetailsResult!!
    }





}