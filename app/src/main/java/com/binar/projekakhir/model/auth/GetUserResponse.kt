package com.binar.projekakhir.model.auth


import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("data")
    val dataUser: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)