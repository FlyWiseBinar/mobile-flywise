package com.binar.projekakhir.model.auth.resetpassword

import com.google.gson.annotations.SerializedName

data class ResetPassPost(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm_password")
    val confirm_password: String,
)
