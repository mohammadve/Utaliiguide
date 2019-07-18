package com.utalli.network

import com.google.gson.JsonObject
import com.utaliiguides.models.responseModel.userRegisterLogin.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {

    @POST(ApiList.LOGIN_URL)
    @FormUrlEncoded
    fun loginUser(
        @Field("mobile_no") mobile_no: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String
    ): Call<JsonObject>

    @POST(ApiList.SEARCH_LOCATION)
    @FormUrlEncoded
    fun getCountries(
        @Field("countryname") countryName: String
    ): Call<JsonObject>

    @POST(ApiList.GET_STATE_BY_COUNTRY)
    @FormUrlEncoded
    fun getStates(
        @Header("x-access-token") token: String,
        @Field("contryid") contryid: Int
    ): Call<JsonObject>



    @POST(ApiList.FORGOT_PASS_URL)
    @FormUrlEncoded
    fun forgotPassword(
        @Field("mobile_no") mobile_no: String
    ): Call<JsonObject>



    @POST(ApiList.RESET_PASS_URL)
    @FormUrlEncoded
    fun resetPassword(
        @Field("password") password: String,
        @Field("otp") otp: String,
        @Field("id") id: Int
    ): Call<JsonObject>


    @POST(ApiList.ADD_BANK_DETAILS)
    @FormUrlEncoded
    fun addBankAccount(
        @Header("x-access-token") token: String,
        @Field("guideId") guideId : Int,
        @Field("account_name") account_name: String,
        @Field("account_no") account_no : String,
        @Field("account_swift_code") account_swift_code : String,
        @Field("branch_no") branch_no : String
    ):Call<JsonObject>


    @POST(ApiList.ALREADY_ADDED_ACCOUNTS_DETAILS)
    @FormUrlEncoded
    fun alreadyAddedAccounts(
        @Header("x-access-token") token: String,
        @Field("guideId") guideId : Int
    ):Call<JsonObject>


    @POST(ApiList.REMOVE_BANK_DETAILS)
    @FormUrlEncoded
    fun removeBankDetails(
        @Header("x-access-token") token: String,
        @Field("guideId") guideId : Int,
        @Field("accountId") accountId : Int
    ): Call<JsonObject>

    @POST(ApiList.SET_DEFAULT_BANK_PAYMENT)
    @FormUrlEncoded
    fun setBankDefaultPayment(
        @Header("x-access-token") token: String,
        @Field("accountId") accountId : Int,
        @Field("guideId") guideId : Int
    ): Call<JsonObject>


}