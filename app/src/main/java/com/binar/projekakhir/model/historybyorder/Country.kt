package com.binar.projekakhir.model.historybyorder


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("continent")
    val continent: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)