package com.binar.projekakhir.model.historybyorder


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id")
    val id: Int,
    @SerializedName("order")
    val order: OrderX,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("schedule")
    val schedule: Schedule,
    @SerializedName("scheduleId")
    val scheduleId: Int
)