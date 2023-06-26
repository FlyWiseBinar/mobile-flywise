package com.binar.projekakhir.model.checkout.request


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("ageType")
    val ageType: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("expiredAt")
    val expiredAt: String,
    @SerializedName("issuingCountry")
    val issuingCountry: String,
    @SerializedName("ktp")
    val ktp: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("passport")
    val passport: String
)