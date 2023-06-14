package com.binar.projekakhir.model.auth.resetpassword

import com.google.gson.annotations.SerializedName

data class UpdateProfilePost(
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("email")
    val email: String
)
