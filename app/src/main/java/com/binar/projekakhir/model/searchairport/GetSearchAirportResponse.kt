package com.binar.projekakhir.model.searchairport


import com.google.gson.annotations.SerializedName

data class GetSearchAirportResponse(
    @SerializedName("airport")
    val airport: List<Airport>,
    @SerializedName("message")
    val message: String
)