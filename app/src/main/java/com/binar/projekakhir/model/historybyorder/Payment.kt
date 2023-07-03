package com.binar.projekakhir.model.historybyorder


import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("id")
    val id: Int,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("paymentCode")
    val paymentCode: String,
    @SerializedName("paymentTypeId")
    val paymentTypeId: Int,
    @SerializedName("status")
    val status: String
)