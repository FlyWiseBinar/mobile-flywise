package com.binar.projekakhir.model.history


import com.google.gson.annotations.SerializedName

data class OriginAirport(
    @SerializedName("airportCode")
    val airportCode: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: Country,
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)