package com.binar.projekakhir.model.auth


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("password")
    val password: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)