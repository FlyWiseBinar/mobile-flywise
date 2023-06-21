package com.binar.projekakhir.model.searchairport


import com.google.gson.annotations.SerializedName

data class GetSearchAirportResponse(
    @SerializedName("data")
    val `data`: List<data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("statue")
    val statue: Boolean
)