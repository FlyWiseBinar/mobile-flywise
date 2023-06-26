package com.binar.projekakhir.model.history


import com.google.gson.annotations.SerializedName

data class Airline(
    @SerializedName("airlineCode")
    val airlineCode: String,
    @SerializedName("airlineName")
    val airlineName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String
)