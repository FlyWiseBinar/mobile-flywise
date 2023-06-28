package com.binar.projekakhir.model.checkout.request


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("ageType")
    var ageType: String?,
    @SerializedName("birthdate")
    var birthdate: String?,
    @SerializedName("expiredAt")
    var expiredAt: String?,
    @SerializedName("issuingCountry")
    var issuingCountry: String?,
    @SerializedName("ktp")
    var ktp: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("nationality")
    var nationality: String?,
    @SerializedName("passport")
    var passport: String?
)

