package com.binar.projekakhir.model.auth


import com.google.gson.annotations.SerializedName

data class GetProfileResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)