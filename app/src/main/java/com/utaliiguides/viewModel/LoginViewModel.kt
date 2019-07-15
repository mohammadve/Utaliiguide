package com.utaliiguides.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utaliiguides.models.responseModel.userRegisterLogin.login.LoginResponse
import com.utalli.helpers.Utils
import com.utalli.network.ApiClient
import com.utalli.network.ApiService
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel(){

    private var loginResult: MutableLiveData<LoginResponse>? = null


    fun loginUser(mContext: Context, mobile_no: String, password: String, device_token:String) : MutableLiveData<LoginResponse>{
        loginResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.loginUser(mobile_no, password, device_token)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<LoginResponse>{

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Utils.hideProgressDialog()
                loginResult!!.value = response.body()
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
        })
        return loginResult!!
    }
}