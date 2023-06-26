package com.binar.projekakhir.model.history


import com.google.gson.annotations.SerializedName

data class GetHistoryResponse(
    @SerializedName("orders")
    val orders: List<Order>
)