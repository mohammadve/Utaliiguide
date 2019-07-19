package com.utaliiguides.models.countryList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StateLatLong {
    @SerializedName("latitude")
    @Expose
    private var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    private var longitude: Double? = null

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