package com.utaliiguides.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.utaliiguides.models.signUpQuestion.GuideRegisterModel
import com.utalli.helpers.AppPreference
import com.utalli.helpers.Utils
import com.utalli.network.ApiClient
import com.utalli.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import java.io.File

class SignUpProcessViewModel : ViewModel() {

    private var signUpResult: MutableLiveData<JsonObject>? = null
    private var countryListResult: MutableLiveData<JsonObject>? = null
    private var stateListResult: MutableLiveData<JsonObject>? = null
    private var questionListResult: MutableLiveData<JsonObject>? = null
    private var guideSignUpResult: MutableLiveData<JsonObject>? = null
    var preference: AppPreference? = null


    fun getCountryList(mContext: Context, countryName: String): MutableLiveData<JsonObject> {
        countryListResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.getCountries(countryName)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject> {

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

    fun getStateList(mContext: Context, selectedCountryId: Int): MutableLiveData<JsonObject> {
        stateListResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var authToken = preference!!.getAuthToken()
        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.getStates(authToken, selectedCountryId)
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject> {

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

    fun getQuestionList(mContext: Context): MutableLiveData<JsonObject> {
        questionListResult = MutableLiveData()
        preference = AppPreference.getInstance(mContext)
        var authToken = preference!!.getAuthToken()
        var apiService = ApiClient.getClient().create(ApiService::class.java)

        var call = apiService.getQuestions("")
        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject> {

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

    fun setUpGuideRegister(mContext: Context, mGuideRegisterDataModel: GuideRegisterModel): MutableLiveData<JsonObject> {
        guideSignUpResult = MutableLiveData()

        var apiService = ApiClient.getClient().create(ApiService::class.java)
        preference = AppPreference.getInstance(mContext)
        var authToken = preference!!.getAuthToken()

//        for (key in mFieldsMap.keys) {
//            Log.e("@@@@@@", "key->" + key);
//
//            if (key.equals("name")) {
//                println("Element at key $key = ${mFieldsMap[key]}")
//
//            }
//        }


        var guideName = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getName())
        var guideEmail = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getEmail())
        var guideMobile = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getMobile_no())
        var guidePassword = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getPassword())
        var guideLanguage = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getLang())
        var guideGender = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getGender())
        var guideDob = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getDob())
        var guideAddress = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getGuideaddress())

        var guideIdCardNo = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getTourist_identity_card_no())

        var idCardRequestFileFront = RequestBody.create(MediaType.parse("image/jpeg"), mGuideRegisterDataModel.getIdentity_front())
        var idCardRequestImgFront = MultipartBody.Part.createFormData("identity_front", mGuideRegisterDataModel.getIdentity_front()?.name, idCardRequestFileFront)

        var idCardRequestFileBack = RequestBody.create(MediaType.parse("image/jpeg"), mGuideRegisterDataModel.getIdentity_back())
        var idCardRequestImgBack = MultipartBody.Part.createFormData("identity_back", mGuideRegisterDataModel.getIdentity_back()?.name, idCardRequestFileBack)

        var guideLocalIdCardNo = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getLocalid_proof_no())

        var localIdCardRequestFileFront = RequestBody.create(MediaType.parse("image/jpeg"), mGuideRegisterDataModel.getLocalid_front())
        var localIdCardRequestImgFront = MultipartBody.Part.createFormData("localid_front", mGuideRegisterDataModel.getLocalid_front()?.name, localIdCardRequestFileFront)

        var localIdCardRequestFileBack = RequestBody.create(MediaType.parse("image/jpeg"), mGuideRegisterDataModel.getLocalid_back())
        var localIdCardRequestImgBack = MultipartBody.Part.createFormData("localid_back", mGuideRegisterDataModel.getLocalid_back()?.name, localIdCardRequestFileBack)

        var guideDrivingLicenceNo = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getDriving_licenceid())

        var drivingLicenceRequestFileFront = RequestBody.create(MediaType.parse("image/jpeg"), mGuideRegisterDataModel.getDriving_licence_front())
        var drivingLicenceRequestImgFront = MultipartBody.Part.createFormData("driving_licence_front", mGuideRegisterDataModel.getDriving_licence_front()?.name, drivingLicenceRequestFileFront)

        var drivingLicenceRequestFileBack = RequestBody.create(MediaType.parse("image/jpeg"), mGuideRegisterDataModel.getDriving_licence_back())
        var drivingLicenceRequestImgBack = MultipartBody.Part.createFormData("driving_licence_back", mGuideRegisterDataModel.getDriving_licence_back()?.name, drivingLicenceRequestFileBack)

        var guideQusAns = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getQuestions())
        var guideAbout = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getGuide_about())
        var guidePoolPrice = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getGuide_pool_price())
        var guidePrivatePrice = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getGuide_private_price())
        var otp = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getOtp())
        var deviceToken = RequestBody.create(MediaType.parse("multipart/form-data"), mGuideRegisterDataModel.getDevice_token())

        var call = apiService.requestGuideRegister(guideName, guideEmail, guideMobile, guidePassword, guideLanguage, guideGender, guideDob, guideAddress,
            guideIdCardNo, idCardRequestImgFront, idCardRequestImgBack,
            guideLocalIdCardNo, localIdCardRequestImgFront, localIdCardRequestImgBack,
            guideDrivingLicenceNo, drivingLicenceRequestImgFront, drivingLicenceRequestImgBack,
            guideQusAns, guideAbout, guidePoolPrice, guidePrivatePrice, otp, deviceToken)

        Utils.showProgressDialog(mContext)

        call.enqueue(object : retrofit2.Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Utils.hideProgressDialog()
                guideSignUpResult!!.value = response.body()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Utils.hideProgressDialog()
                Utils.showLog(t.message!!)
            }
        })
        return guideSignUpResult!!
    }
}