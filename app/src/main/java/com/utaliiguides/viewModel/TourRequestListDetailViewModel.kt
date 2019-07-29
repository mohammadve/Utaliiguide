package com.utaliiguides.viewModel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import com.utalli.network.ApiClient
import com.utalli.network.ApiService
import retrofit2.Call
import retrofit2.Response

class TourRequestListDetailViewModel : ViewModel(){
   private var tourRequestListDetailResult : MutableLiveData<JsonObject> ?= null
    private var preference : AppPreference ?= null



    fun getTourRequestListDetail(mContext : Context, tour_req_id: Int): MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        var token = preference!!.getAuthToken()

        tourRequestListDetailResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.requestListDetail(token,tour_req_id)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null && response.body()!= null){
                    tourRequestListDetailResult!!.value = response.body()
                }
                else{

                }
            }

        })

       return tourRequestListDetailResult!!
    }











}