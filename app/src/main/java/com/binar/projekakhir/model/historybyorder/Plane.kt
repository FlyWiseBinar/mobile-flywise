package com.binar.projekakhir.model.historybyorder


import com.google.gson.annotations.SerializedName

data class Plane(
    @SerializedName("airline")
    val airline: Airline,
    @SerializedName("airlineId")
    val airlineId: Int,
    @SerializedName("baggageMaxCapacity")
    val baggageMaxCapacity: Int,
    @SerializedName("cabinMaxCapacity")
    val cabinMaxCapacity: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("passengerCapacity")
    val passengerCapacity: Int
)