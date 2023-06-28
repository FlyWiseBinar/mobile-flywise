package com.binar.projekakhir.model.paymentcreate


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("paymentCode")
    val paymentCode: String,
    @SerializedName("paymentTypeId")
    val paymentTypeId: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)