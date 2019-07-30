package com.utaliiguides.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import com.utalli.network.ApiClient
import com.utalli.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

class MyProfileViewModel : ViewModel(){

    private var guideUpdateProfilePicResult: MutableLiveData<JsonObject>? = null
    private var guideUpdateProfileResult: MutableLiveData<JsonObject>? = null
    private var guideProfileResult : MutableLiveData<JsonObject> ?= null
    private var changeGuideDuty : MutableLiveData<JsonObject> ?=null
    var preference: AppPreference? = null



    fun setGuideProfile(mContext : Context) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        var guideId = preference!!.getId()


        guideProfileResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.setGuideProfile(token, guideId)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    guideProfileResult!!.value = response.body()
                }
            }
        })

        return  guideProfileResult!!
    }





    fun updateProfilePic(mContext : Context, pic: MultipartBody.Part) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)


        guideUpdateProfilePicResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var id = RequestBody.create(MediaType.parse("multipart/form-data"), ""+preference!!.getId() as Int)

        var call = apiService.updateProfilePic(id,pic)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
              //  Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response!= null && response.body()!= null){
                    guideUpdateProfilePicResult!!.value = response.body()
                }
            }
        })

        return guideUpdateProfilePicResult!!
    }

    fun updateProfile(mContext : Context, name : String, guide_email: String, contactno: String, emry_contact: String, guide_address:String) : MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        preference = AppPreference.getInstance(mContext)
        val token = preference!!.getAuthToken()
        var id = preference!!.getId()

        guideUpdateProfileResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.updateProfile(token, name, guide_email, contactno, emry_contact, guide_address, id)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()

                if(response!= null && response.body()!= null){
                    guideUpdateProfileResult!!.value = response.body()
                }
            }

        })

        return guideUpdateProfileResult!!

    }



    fun changeGuideDuty(mContext : Context, duty : Int): MutableLiveData<JsonObject>{
        preference = AppPreference.getInstance(mContext)
        var guideId = preference!!.getId()
        var token = preference!!.getAuthToken()

        changeGuideDuty = MutableLiveData()
      /*  var value=0
        if(duty){
            value=1
        }*/

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        var call = apiService.changeGuideDuty(token, guideId, duty)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject>{
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                if(response!= null && response.body()!= null){
                    changeGuideDuty!!.value = response.body()
                }
            }
        })


        return changeGuideDuty!!
    }










}