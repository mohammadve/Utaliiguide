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
import java.text.ParseException
import java.text.SimpleDateFormat

class CreateNewTourViewModel : ViewModel(){
    var createPoolTourResult : MutableLiveData<JsonObject> ?= null
    var createPrivateTourResult : MutableLiveData<JsonObject> ?= null
    var myCreatedTourDataResult : MutableLiveData<JsonObject> ?= null

    var preference : AppPreference?= null



    fun getMyCreatedTourData(mContext : Context) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()
        myCreatedTourDataResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.getMyCreatedTourData(token, guideId)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null && response.body()!= null){
                    myCreatedTourDataResult!!.value = response.body()
                }
            }
        })
        return myCreatedTourDataResult!!
    }





    fun createPoolTour(mContext : Context, requesttype: Int, tour_mem_id : Int,
                       startdate : String, enddate :String, tourNotes : String, no_of_members : Int,
                       userId: Int, tour_price : Int) : MutableLiveData<JsonObject>{

        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()
        createPoolTourResult = MutableLiveData()


        var startDate = startdate
        var endDate = enddate


        try {
            val sdfSource = SimpleDateFormat("dd/MM/yyyy")
            val date = sdfSource.parse(startDate)
            val sdfDestination = SimpleDateFormat("yyyy-MM-dd")
            startDate = sdfDestination.format(date)
        } catch (pe: ParseException) {
            println("Parse Exception : $pe")
        }


        try {
            val sdfSource = SimpleDateFormat("dd/MM/yyyy")
            val date = sdfSource.parse(endDate)
            val sdfDestination = SimpleDateFormat("yyyy-MM-dd")
            endDate = sdfDestination.format(date)
        } catch (pe: ParseException) {
            println("Parse Exception : $pe")
        }




        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.createPoolTour(token, guideId, requesttype, tour_mem_id,startDate, endDate,tourNotes,no_of_members,userId,tour_price)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    createPoolTourResult!!.value = response.body()
                }

            }
        })

        return createPoolTourResult!!
    }






    fun createPrivateTour(mContext : Context, requesttype : Int, tour_price : Int) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        val guideId = preference!!.getId()
        createPrivateTourResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.createPrivateTour(token,guideId,requesttype,tour_price)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response != null && response.body()!= null){
                  createPrivateTourResult!!.value = response.body()
                }
            }
        })

        return createPrivateTourResult!!
    }




}