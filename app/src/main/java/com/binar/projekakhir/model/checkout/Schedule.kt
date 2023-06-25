package com.binar.projekakhir.model.checkout


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("scheduleId")
    val scheduleId: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)