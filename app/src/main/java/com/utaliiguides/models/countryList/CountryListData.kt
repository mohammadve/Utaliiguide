package com.utaliiguides.models.countryList

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryListData {
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("sortname")
    @Expose
    private var sortname: String? = null

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

    fun getSortname(): String? {
        return sortname
    }

    fun setSortname(sortname: String) {
        this.sortname = sortname
    }
}