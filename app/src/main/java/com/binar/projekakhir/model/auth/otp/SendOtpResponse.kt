package com.binar.projekakhir.model.auth.otp


import com.google.gson.annotations.SerializedName

data class SendOtpResponse(
    @SerializedName("message")
    val message: String
)