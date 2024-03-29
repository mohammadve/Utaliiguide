package com.utalli.network

import com.google.gson.JsonObject
import com.utaliiguides.models.responseModel.userRegisterLogin.login.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Multipart




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

    @POST(ApiList.GET_QUESTIONS_LIST)
    @FormUrlEncoded
    fun getQuestions(
        @Field("id") testId: String
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

    @POST(ApiList.GET_TOUR_REQUEST)
    @FormUrlEncoded
    fun getTourRequest(
        @Header("x-access-token") token: String,
        @Field("guideId") guideId : Int
    ): Call<JsonObject>


    @POST(ApiList.ACCEPT_TOUR_REQUEST)
    @FormUrlEncoded
    fun acceptTourRequest(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId : Int,
        @Field("tourrequestId") tourrequestId : Int,
        @Field("requeststatus") requeststatus : Int
    ): Call<JsonObject>

    @POST(ApiList.REJECT_TOUR_REQUEST)
    @FormUrlEncoded
    fun rejectNewTourRequest(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId : Int,
        @Field("tourrequestId") tourrequestId : Int,
        @Field("requeststatus") requeststatus : Int
    ): Call<JsonObject>


    @Multipart
    @POST(ApiList.UPDATE_GUIDE_PROFILE_IMAGE)
    fun updateProfilePic(
        @Part("id") id: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<JsonObject>

    @POST(ApiList.UPDATE_PROFILE)
    @FormUrlEncoded
    fun updateProfile(
        @Header("x-access-token") token : String,
        @Field("name") name : String,
        @Field("guide_email") guide_email: String,
        @Field("contactno") contactno: String,
        @Field("emry_contact") emry_contact: String,
        @Field("guide_address") guide_address : String,
        @Field("id") id : Int
    ):Call<JsonObject>

    @POST(ApiList.CHANGE_GUIDE_DUTY)
    @FormUrlEncoded
    fun changeGuideDuty(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId : Int,
        @Field("duty") duty : Int

    ):Call<JsonObject>


    @POST(ApiList.GUIDE_PROFILE_INFO)
    @FormUrlEncoded
    fun setGuideProfile(
        @Header("x-access-token") token : String,
        @Field("guidId") guidId : Int
    ):Call<JsonObject>


    @POST(ApiList.GUIDE_TRIPS_LIST_URL)
    @FormUrlEncoded
    fun getGuideTripList(
        @Header("x-access-token") token: String,
        @Field("guideId") guideId : Int
    ):Call<JsonObject>


    @POST(ApiList.PAYMENT_TOTAL_EARNINGS)
    @FormUrlEncoded
    fun getPayment_totalEarnings(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId : Int
    ):Call<JsonObject>


    @POST(ApiList.TODAY_TRIP_LIST)
    @FormUrlEncoded
    fun getTodayTripList(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId :Int
    ):Call<JsonObject>


    @POST(ApiList.ABOUT_US)
    fun aboutUs(
        @Header("x-access-token") token : String
    ):Call<JsonObject>

    @POST(ApiList.PRIVACY_POLICY)
    fun privacyPolicy(
        @Header("x-access-token") token : String
    ):Call<JsonObject>

    @POST(ApiList.HELP_AND_SUPPORT)
    fun helpAndSupport(
        @Header("x-access-token") token : String
    ):Call<JsonObject>


    @POST(ApiList.ABOUT_APP)
    fun aboutApp(
        @Header("x-access-token") token : String
    ):Call<JsonObject>


    @POST(ApiList.REQUEST_LIST_DETAIL)
    @FormUrlEncoded
    fun requestListDetail(
        @Header("x-access-token") token : String,
        @Field("req_id") req_id : Int
    ):Call<JsonObject>

    @Multipart
    @POST(ApiList.REQUEST_GUIDE_REGISTER)
    fun requestGuideRegister(
        @Part("name") guideName : RequestBody,
        @Part("email") guideEmail : RequestBody,
        @Part("mobile_no") guideMobile : RequestBody,
        @Part("password") guidePassword : RequestBody,
        @Part("lang") guideLanguage : RequestBody,
        @Part("gender") guideGender : RequestBody,
        @Part("dob") guideDob : RequestBody,
        @Part("guideaddress") guideAddress : RequestBody,

        @Part("tourist_identity_card_no") guideIdCardNo : RequestBody,
        @Part guideIdCardFront : MultipartBody.Part,
        @Part guideIdCardBack : MultipartBody.Part,

        @Part("localid_proof_no") guideLocalIdCardNo : RequestBody,
        @Part guideLocalIdCardFront : MultipartBody.Part,
        @Part guideLocalIdCardBack : MultipartBody.Part,

        @Part("driving_licenceid") guideDrivingLicenceNo : RequestBody,
        @Part guideDrivingLicenceFront : MultipartBody.Part,
        @Part guideDrivingLicenceBack : MultipartBody.Part,

        @Part("questions") guideQusAns : RequestBody,
        @Part("guide_about") guideAbout : RequestBody,
        @Part("guide_pool_price") guidePoolPrice : RequestBody,
        @Part("guide_private_price") guidePrivatePrice : RequestBody,
        @Part("otp") otp : RequestBody,
        @Part("device_token") deviceToken : RequestBody
    ):Call<JsonObject>

    @POST(ApiList.CREATE_POOL_TOUR)
    @FormUrlEncoded
    fun createPoolTour(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId:Int,
        @Field("requesttype") requesttype : Int,
        @Field("tour_mem_id") tour_mem_id : Int,
        @Field("startdate") startdate : String,
        @Field("enddate") enddate : String,
        @Field("TourNotes") tourNotes : String,
        @Field("no_of_members") no_of_members : Int,
        @Field("userId") userId : Int,
        @Field("tour_price") tour_price : Int
    ):Call<JsonObject>


    @POST(ApiList.CREATE_POOL_TOUR)
    @FormUrlEncoded
    fun createPrivateTour(
        @Header("x-access-token") token: String,
        @Field("guideId") guideId:Int,
        @Field("requesttype") requesttype: Int,
        @Field("tour_price") tour_price: Int
    ):Call<JsonObject>

    @POST(ApiList.MY_CREATED_TOUR_URL)
    @FormUrlEncoded
    fun getMyCreatedTourData(
        @Header("x-access-token") token : String,
        @Field("guideId") guideId : Int
    ):Call<JsonObject>
}