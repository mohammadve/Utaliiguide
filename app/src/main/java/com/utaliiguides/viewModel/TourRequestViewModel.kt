package com.utaliiguides.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import com.utalli.network.ApiClient
import com.utalli.network.ApiService
import retrofit2.Call
import retrofit2.Response


class TourRequestViewModel : ViewModel(){

    private var tourRequestResult : MutableLiveData<JsonObject> ?= null
    private var acceptNewTourRequestResult : MutableLiveData<JsonObject> ?= null
    private var rejectNewTourRequestResult : MutableLiveData<JsonObject> ?= null
    var preference : AppPreference?= null


    fun getTourRequest(mContext : Context): MutableLiveData<JsonObject>{

        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()

        tourRequestResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getTourRequest(token, guideId)

        Utils.showProgressDialog(mContext)


        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body() != null){
                    tourRequestResult!!.value = response.body()
                }
            }
        })

        return tourRequestResult!!
    }



    fun acceptNewTourRequest(mContext : Context, tourrequestId : Int, requeststatus : Int) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()

        acceptNewTourRequestResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.acceptTourRequest(token, guideId, tourrequestId, requeststatus)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null && response.body()!= null){
                    acceptNewTourRequestResult!!.value = response.body()
                }
            }
        })


        return acceptNewTourRequestResult!!
    }

    fun rejectNewTourRequest(mContext : Context, tourrequestId : Int, requeststatus : Int) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()

        rejectNewTourRequestResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.rejectNewTourRequest(token, guideId, tourrequestId, requeststatus)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body() != null){
                    rejectNewTourRequestResult!!.value = response.body()
                }
            }

        })




          return rejectNewTourRequestResult!!

    }






}