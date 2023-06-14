package com.binar.projekakhir.model.auth.otp


import com.google.gson.annotations.SerializedName

data class VerifyOtpResponse(
    @SerializedName("message")
    val message: String? = null,
)

data class VerOtp(

    @field:SerializedName("email")
    val email: String? = null,
    @field:SerializedName("otp")
    val otp: String? = null,

    )