package com.binar.projekakhir.model.auth


import com.google.gson.annotations.SerializedName

data class VerifyOtpResponse(
    @SerializedName("message")
    val message: String
)