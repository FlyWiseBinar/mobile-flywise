package com.binar.projekakhir.model.auth.resetpassword


import com.google.gson.annotations.SerializedName

data class SendOtpResponse(
    @SerializedName("expiredAt")
    val expiredAt: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)