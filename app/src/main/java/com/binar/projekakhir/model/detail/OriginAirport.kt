package com.binar.projekakhir.model.detail


import com.google.gson.annotations.SerializedName

data class OriginAirport(
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String
)