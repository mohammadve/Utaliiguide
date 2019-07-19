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

class SignUpProcessViewModel : ViewModel(){

    private var signUpResult: MutableLiveData<JsonObject>? = null
    private var countryListResult: MutableLiveData<JsonObject>? = null
    private var stateListResult: MutableLiveData<JsonObject>? = null
    private var questionListResult: MutableLiveData<JsonObject>? = null
    var preference: AppPreference? = null


    fun getCountryList(mContext: Context, countryName: String) : MutableLiveData<JsonObject>{
        countryListResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.getCountries(countryName)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                countryListResult!!.value = response.body()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
        })
        return countryListResult!!
    }

    fun getStateList(mContext: Context, selectedCountryId: Int) : MutableLiveData<JsonObject>
    {
        stateListResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var authToken = preference!!.getAuthToken()
        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.getStates(authToken, selectedCountryId)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                stateListResult!!.value = response.body()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
        })
        return stateListResult!!
    }

    fun getQuestionList(mContext: Context) : MutableLiveData<JsonObject>
    {
        questionListResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var authToken = preference!!.getAuthToken()
        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.getQuestions(authToken, "")
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                questionListResult!!.value = response.body()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
        })
        return questionListResult!!
    }
}