package com.binar.projekakhir.model.auth.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("expiredAt")
    val expiredAt: String,
    @SerializedName("status")
    val status: Boolean
)