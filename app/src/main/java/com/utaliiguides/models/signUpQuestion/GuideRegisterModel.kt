package com.utaliiguides.models.signUpQuestion

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.File

class GuideRegisterModel {

    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("mobile_no")
    @Expose
    private var mobile_no: String? = null
    @SerializedName("password")
    @Expose
    private var password: String? = null
    @SerializedName("lang")
    @Expose
    private var lang: String? = null

    @SerializedName("gender")
    @Expose
    private var gender: String? = null
    @SerializedName("device_token")
    @Expose
    private var device_token: String? = null
    @SerializedName("guide_private_price")
    @Expose
    private var guide_private_price: String? = null
    @SerializedName("guide_pool_price")
    @Expose
    private var guide_pool_price: String? = null
    @SerializedName("guide_about")
    @Expose
    private var guide_about: String? = null
    @SerializedName("guideaddress")
    @Expose
    private var guideaddress: String? = null
    @SerializedName("tourist_identity_card_no")
    @Expose
    private var tourist_identity_card_no: String? = null
    @SerializedName("identity_front")
    @Expose
    private var identity_front: File? = null
    @SerializedName("identity_back")
    @Expose
    private var identity_back: File? = null

    @SerializedName("localid_proof_no")
    @Expose
    private var localid_proof_no: String? = null
    @SerializedName("localid_front")
    @Expose
    private var localid_front: File? = null
    @SerializedName("localid_back")
    @Expose
    private var localid_back: File? = null

    @SerializedName("driving_licenceid")
    @Expose
    private var driving_licenceid: String? = null
    @SerializedName("driving_licence_front")
    @Expose
    private var driving_licence_front: File? = null
    @SerializedName("driving_licence_back")
    @Expose
    private var driving_licence_back: File? = null

    @SerializedName("questions")
    @Expose
    private var questions: String? = null

    @SerializedName("otp")
    @Expose
    private var otp: String? = null

    @SerializedName("dob")
    @Expose
    private var dob: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getMobile_no(): String? {
        return mobile_no
    }

    fun setMobile_no(mobile_no: String) {
        this.mobile_no = mobile_no
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getLang(): String? {
        return lang
    }

    fun setLang(lang: String) {
        this.lang = lang
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getDevice_token(): String? {
        return device_token
    }

    fun setDevice_token(device_token: String) {
        this.device_token = device_token
    }

    fun getGuide_private_price(): String? {
        return guide_private_price
    }

    fun setGuide_private_price(guide_private_price: String) {
        this.guide_private_price = guide_private_price
    }

    fun getGuide_pool_price(): String? {
        return guide_pool_price
    }

    fun setGuide_pool_price(guide_pool_price: String) {
        this.guide_pool_price = guide_pool_price
    }

    fun getGuide_about(): String? {
        return guide_about
    }

    fun setGuide_about(guide_about: String) {
        this.guide_about = guide_about
    }

    fun getGuideaddress(): String? {
        return guideaddress
    }

    fun setGuideaddress(guideaddress: String) {
        this.guideaddress = guideaddress
    }

    fun getTourist_identity_card_no(): String? {
        return tourist_identity_card_no
    }

    fun setTourist_identity_card_no(tourist_identity_card_no: String) {
        this.tourist_identity_card_no = tourist_identity_card_no
    }

    fun getIdentity_front(): File? {
        return identity_front
    }

    fun setIdentity_front(identity_front: File) {
        this.identity_front = identity_front
    }

    fun getIdentity_back(): File? {
        return identity_back
    }

    fun setIdentity_back(identity_back: File) {
        this.identity_back = identity_back
    }

    fun getLocalid_proof_no(): String? {
        return localid_proof_no
    }

    fun setLocalid_proof_no(localid_proof_no: String) {
        this.localid_proof_no = localid_proof_no
    }

    fun getLocalid_front(): File? {
        return localid_front
    }

    fun setLocalid_front(localid_front: File) {
        this.localid_front = localid_front
    }

    fun getLocalid_back(): File? {
        return localid_back
    }

    fun setLocalid_back(localid_back: File) {
        this.localid_back = localid_back
    }

    fun getDriving_licenceid(): String? {
        return driving_licenceid
    }

    fun setDriving_licenceid(driving_licenceid: String) {
        this.driving_licenceid = driving_licenceid
    }

    fun getDriving_licence_front(): File? {
        return driving_licence_front
    }

    fun setDriving_licence_front(driving_licence_front: File) {
        this.driving_licence_front = driving_licence_front
    }

    fun getDriving_licence_back(): File? {
        return driving_licence_back
    }

    fun setDriving_licence_back(driving_licence_back: File) {
        this.driving_licence_back = driving_licence_back
    }

    fun getQuestions(): String? {
        return questions
    }

    fun setQuestions(questions: String) {
        this.questions = questions
    }

    fun getOtp(): String? {
        return otp
    }

    fun setOtp(otp: String) {
        this.otp = otp
    }

    fun getDob(): String? {
        return dob
    }

    fun setDob(dob: String) {
        this.dob = dob
    }

}