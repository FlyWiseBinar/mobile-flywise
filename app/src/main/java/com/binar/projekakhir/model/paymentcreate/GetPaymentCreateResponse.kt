package com.binar.projekakhir.model.paymentcreate


import com.google.gson.annotations.SerializedName

data class GetPaymentCreateResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)