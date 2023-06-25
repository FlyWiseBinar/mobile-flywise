package com.binar.projekakhir.model.checkout


import com.google.gson.annotations.SerializedName

data class PostCheckoutResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)