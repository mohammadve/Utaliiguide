package com.utaliiguides.models.responseModel.userRegisterLogin.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class LoginResponse {
    @SerializedName("status")
    @Expose
    private var status: Int? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null
    @SerializedName("auth")
    @Expose
    private var auth: Boolean? = null
    @SerializedName("accessToken")
    @Expose
    private var accessToken: String? = null
    @SerializedName("data")
    @Expose
    private var data: LoginUserData? = null

    fun getStatus(): Int? {
        return status
    }

    fun setStatus(status: Int?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getAuth(): Boolean? {
        return auth
    }

    fun setAuth(auth: Boolean?) {
        this.auth = auth
    }

    fun getAccessToken(): String? {
        return accessToken
    }

    fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    fun getData(): LoginUserData? {
        return data
    }

    fun setData(data: LoginUserData) {
        this.data = data
    }
}