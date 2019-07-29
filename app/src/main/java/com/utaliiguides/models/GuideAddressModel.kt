package com.utaliiguides.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GuideAddressModel {

    @SerializedName("countryCode")
    @Expose
    private var countryCode: String? = null
    @SerializedName("countryId")
    @Expose
    private var countryId: String? = null
    @SerializedName("country_name")
    @Expose
    private var countryName: String? = null
    @SerializedName("stateId")
    @Expose
    private var stateId: Int? = null
    @SerializedName("statename")
    @Expose
    private var statename: String? = null
    @SerializedName("latitude")
    @Expose
    private var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    private var longitude: Double? = null

    fun getCountryCode(): String? {
        return countryCode
    }

    fun setCountryCode(countryCode: String) {
        this.countryCode = countryCode
    }

    fun getCountryId(): String? {
        return countryId
    }

    fun setCountryId(countryId: String) {
        this.countryId = countryId
    }

    fun getCountryName(): String? {
        return countryName
    }

    fun setCountryName(countryName: String) {
        this.countryName = countryName
    }

    fun getStateId(): Int? {
        return stateId
    }

    fun setStateId(stateId: Int?) {
        this.stateId = stateId
    }

    fun getStatename(): String? {
        return statename
    }

    fun setStatename(statename: String) {
        this.statename = statename
    }

    fun getLatitude(): Double? {
        return latitude
    }

    fun setLatitude(latitude: Double?) {
        this.latitude = latitude
    }

    fun getLongitude(): Double? {
        return longitude
    }

    fun setLongitude(longitude: Double?) {
        this.longitude = longitude
    }
}