package com.binar.projekakhir.model.historybyorder


import com.binar.projekakhir.model.history.Order
import com.google.gson.annotations.SerializedName

data class ResponseHistoryByCode(
    @SerializedName("orders")
    val orders: List<com.binar.projekakhir.model.historybyorder.Order>
)