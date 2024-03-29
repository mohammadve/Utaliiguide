package com.utalli.network

class ApiList {
    companion object {

        val BASE_URL = "http://3.13.3.42:8000/api/v1/"

        const val SIGN_UP_URL = "guides/guideregister"
        const val VERIFY_OTP_URL = "users/register"
        const val LOGIN_URL = "guides/login"
        const val FORGOT_PASS_URL = "guides/forgetPassword"
        const val UPDATE_PASS_URL = "guides/resetPassword"

        const val SEARCH_LOCATION = "deshboard/searchLocation"
        const val GET_STATE_BY_COUNTRY = "deshboard/getstateBycountry"
        const val GET_QUESTIONS_LIST = "guides/questionsList"
      //  const val UPDATE_PASS_URL = "guides/resetPassword"
        const val RESET_PASS_URL = "guides/resetPassword"
        const val ADD_BANK_DETAILS = "guides/addbankdetails"
        const val ALREADY_ADDED_ACCOUNTS_DETAILS = "guides/bankdetailsList"
        const val REMOVE_BANK_DETAILS = "guides/removeBankdetails"
        const val SET_DEFAULT_BANK_PAYMENT = "guides/setdefaultbank"
        const val GET_TOUR_REQUEST = "guides/tourRequestList"
        const val ACCEPT_TOUR_REQUEST = "guides/tourRequestAction"
        const val REJECT_TOUR_REQUEST = "guides/tourRequestAction"
        const val UPDATE_GUIDE_PROFILE_IMAGE = "guides/UpdateuserProfileimage"
        const val UPDATE_PROFILE = "guides/UpdateuserProfile"
        const val CHANGE_GUIDE_DUTY = "guides/changeguideduty"
        const val GUIDE_TRIPS_LIST_URL = "guides/guide_trips"
        const val GUIDE_PROFILE_INFO = "guide/guidinformation"
        const val PAYMENT_TOTAL_EARNINGS = "guides/payments"
        const val TODAY_TRIP_LIST = "guides/todaytrip"

        const val ABOUT_US = "guide/guideappaboutus"
        const val PRIVACY_POLICY = "guide/guidappprivacypolicies"
        const val HELP_AND_SUPPORT = "guide/guideapphelpandsupport"
        const val ABOUT_APP = "guide/appinfo"

        const val REQUEST_LIST_DETAIL = "guides/request_details"
        const val CREATE_POOL_TOUR = "guides/addTours"
        const val MY_CREATED_TOUR_URL ="guides/myCreatedTour"

        const val REQUEST_GUIDE_REGISTER = "guides/guideregister_new"












/*
        const val UPDATE_PROFILE_PIC_URL = "user/update_profile_pic"
        const val GET_CREDENTIALS_URL = "user/credantials?search=all"
        const val SENT_PATIENT_REFERAL_URL = "patient/patient_refferel"
        const val GET_PROVIDERS_URL = "contact/contactprovider"
        const val GET_VIDEOS_URL = "education/get_videos"
        const val INVITE_VIDEO_CALLING_URL = "contact/invite_calling"
        const val REJECT_VIDEO_CALLING_URL = "contact/reject_calling"
        const val GET_NOTIFICATIONS_URL = "user/notifications"
        const val SEND_NOTIFICATION_URL = "contact/send_message"
        const val RESET_PASS_URL = "users/resetPassword"


        const val SEARCH_LOCATION = "deshboard/searchLocation"
        const val GET_STATE_BY_COUNTRY = "deshboard/getstateBycountry"
        const val GUIDE_SEARCH = "deshboard/guidsearch"
        const val MY_PROFILE_DETAILS = "users/userinformation"
        const val GUIDE_INFORMATION = "guide/guidinformation"
        const val UPDATE_USER_PROFILE_IMAGE = "users/UpdateuserProfileimage"
        const val UPDATE_PROFILE_URL = "users/UpdateuserProfile"
        const val REQ_SEND_TO_GUIDE ="guide/requesttoguide"
        const val SEND_REQUEST_STATUS ="guide/cancelrequest"
        const val HELP_AND_SUPPORT = "guide/helpandsupport"
        const val ADD_PAYMENT_CARD = "users/addcard"
        const val GET_CARD_DETAILS = "users/getcards"
        const val UPDATE_DEVICE_TOKEN = "users/updatetoken"


        var ABOUT_US_URL = BASE_URL + "user/about"
        var TERMS_CONDITION_URL = BASE_URL + "user/terms-condition"
*/
    }
}