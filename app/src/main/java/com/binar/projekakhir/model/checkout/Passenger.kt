package com.binar.projekakhir.model.checkout


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("ageType")
    val ageType: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("createdAt")
    val createdAt: String ,
    @SerializedName("expiredAt")
    val expiredAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("issuingCountry")
    val issuingCountry: String,
    @SerializedName("ktp")
    val ktp: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("passport")
    val passport: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)