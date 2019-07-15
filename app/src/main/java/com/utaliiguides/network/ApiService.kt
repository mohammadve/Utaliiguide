package com.utalli.network

import com.google.gson.JsonObject
import com.utaliiguides.models.responseModel.userRegisterLogin.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiService {

    @POST(ApiList.LOGIN_URL)
    @FormUrlEncoded
    fun loginUser(
        @Field("mobile_no") mobile_no: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String
    ): Call<LoginResponse>

}