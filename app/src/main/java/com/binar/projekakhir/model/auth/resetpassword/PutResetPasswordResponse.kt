package com.binar.projekakhir.model.auth.resetpassword


import com.google.gson.annotations.SerializedName

data class PutResetPasswordResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)