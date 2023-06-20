package com.binar.projekakhir.model.searchtiket


import com.google.gson.annotations.SerializedName

data class OriginAirport(
    @SerializedName("airportCode")
    val airportCode: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)