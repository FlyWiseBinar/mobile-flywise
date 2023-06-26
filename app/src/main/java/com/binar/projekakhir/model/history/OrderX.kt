package com.binar.projekakhir.model.history


import com.google.gson.annotations.SerializedName

data class OrderX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("orderCode")
    val orderCode: String,
    @SerializedName("passengers")
    val passengers: List<Passenger>,
    @SerializedName("payment")
    val payment: List<Any>,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("user")
    val user: User,
    @SerializedName("userId")
    val userId: Int
)