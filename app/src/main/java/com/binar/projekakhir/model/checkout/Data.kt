package com.binar.projekakhir.model.checkout


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("orderCode")
    val orderCode: String,
    @SerializedName("passenger")
    val passenger: List<Passenger>,
    @SerializedName("payment")
    val payment: Payment,
    @SerializedName("schedule")
    val schedule: List<Schedule>,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("userId")
    val userId: Int
)