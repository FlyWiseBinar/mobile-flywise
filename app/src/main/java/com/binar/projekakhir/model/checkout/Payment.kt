package com.binar.projekakhir.model.checkout


import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("paymentCode")
    val paymentCode: String,
    @SerializedName("paymentTypeId")
    val paymentTypeId: Any,
    @SerializedName("status")
    val status: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)