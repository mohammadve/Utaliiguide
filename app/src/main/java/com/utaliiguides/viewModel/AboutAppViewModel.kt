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

class AboutAppViewModel : ViewModel(){

    private var aboutAppResult: MutableLiveData<JsonObject>? = null
    var preference : AppPreference?= null


    fun aboutApp(mContext : Context) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()

        aboutAppResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService!!.aboutApp(token)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body() != null){
                    aboutAppResult!!.value =  response.body()
                }
            }

        })


        return aboutAppResult!!
    }




}