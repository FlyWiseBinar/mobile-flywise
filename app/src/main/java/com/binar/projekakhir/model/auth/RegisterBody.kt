package com.binar.projekakhir.model.auth

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val telephone : String

)
