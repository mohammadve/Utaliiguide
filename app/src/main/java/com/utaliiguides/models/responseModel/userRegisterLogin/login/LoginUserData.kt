package com.utaliiguides.models.responseModel.userRegisterLogin.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class LoginUserData {
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("gender")
    @Expose
    private var gender: String? = null
    @SerializedName("lang")
    @Expose
    private var lang: String? = null
    @SerializedName("guide_email")
    @Expose
    private var guideEmail: String? = null
    @SerializedName("guide_mobile_no")
    @Expose
    private var guideMobileNo: String? = null
    @SerializedName("is_verified")
    @Expose
    private var isVerified: Boolean? = null
    @SerializedName("password")
    @Expose
    private var password: String? = null
    @SerializedName("guid_profile_img")
    @Expose
    private var guidProfileImg: Any? = null
    @SerializedName("guide_private_price")
    @Expose
    private var guidePrivatePrice: Int? = null
    @SerializedName("guide_pool_price")
    @Expose
    private var guidePoolPrice: Int? = null
    @SerializedName("guiderating")
    @Expose
    private var guiderating: Any? = null
    @SerializedName("guide_about")
    @Expose
    private var guideAbout: String? = null
    @SerializedName("device_token")
    @Expose
    private var deviceToken: String? = null
    @SerializedName("verification_code")
    @Expose
    private var verificationCode: String? = null
    @SerializedName("booking_status")
    @Expose
    private var bookingStatus: Int? = null
    @SerializedName("createdAt")
    @Expose
    private var createdAt: String? = null
    @SerializedName("updatedAt")
    @Expose
    private var updatedAt: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getGender(): String? {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getLang(): String? {
        return lang
    }

    fun setLang(lang: String) {
        this.lang = lang
    }

    fun getGuideEmail(): String? {
        return guideEmail
    }

    fun setGuideEmail(guideEmail: String) {
        this.guideEmail = guideEmail
    }

    fun getGuideMobileNo(): String? {
        return guideMobileNo
    }

    fun setGuideMobileNo(guideMobileNo: String) {
        this.guideMobileNo = guideMobileNo
    }

    fun getIsVerified(): Boolean? {
        return isVerified
    }

    fun setIsVerified(isVerified: Boolean?) {
        this.isVerified = isVerified
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getGuidProfileImg(): Any? {
        return guidProfileImg
    }

    fun setGuidProfileImg(guidProfileImg: Any) {
        this.guidProfileImg = guidProfileImg
    }

    fun getGuidePrivatePrice(): Int? {
        return guidePrivatePrice
    }

    fun setGuidePrivatePrice(guidePrivatePrice: Int?) {
        this.guidePrivatePrice = guidePrivatePrice
    }

    fun getGuidePoolPrice(): Int? {
        return guidePoolPrice
    }

    fun setGuidePoolPrice(guidePoolPrice: Int?) {
        this.guidePoolPrice = guidePoolPrice
    }

    fun getGuiderating(): Any? {
        return guiderating
    }

    fun setGuiderating(guiderating: Any) {
        this.guiderating = guiderating
    }

    fun getGuideAbout(): String? {
        return guideAbout
    }

    fun setGuideAbout(guideAbout: String) {
        this.guideAbout = guideAbout
    }

    fun getDeviceToken(): String? {
        return deviceToken
    }

    fun setDeviceToken(deviceToken: String) {
        this.deviceToken = deviceToken
    }

    fun getVerificationCode(): String? {
        return verificationCode
    }

    fun setVerificationCode(verificationCode: String) {
        this.verificationCode = verificationCode
    }

    fun getBookingStatus(): Int? {
        return bookingStatus
    }

    fun setBookingStatus(bookingStatus: Int?) {
        this.bookingStatus = bookingStatus
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt(updatedAt: String) {
        this.updatedAt = updatedAt
    }
}