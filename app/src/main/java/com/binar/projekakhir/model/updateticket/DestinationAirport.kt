package com.binar.projekakhir.model.updateticket


import com.google.gson.annotations.SerializedName

data class DestinationAirport(
    @SerializedName("city")
    val city: String,
    @SerializedName("name")
    val name: String
)