package com.binar.projekakhir.model.favorite


import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("adultPrice")
    val adultPrice: Int,
    @SerializedName("arrivedDate")
    val arrivedDate: String,
    @SerializedName("arrivedTime")
    val arrivedTime: String,
    @SerializedName("available_seat")
    val availableSeat: Int,
    @SerializedName("babyPrice")
    val babyPrice: Int,
    @SerializedName("class")
    val classX: Class,
    @SerializedName("departureDate")
    val departureDate: String,
    @SerializedName("departureTime")
    val departureTime: String,
    @SerializedName("destinationAirport")
    val destinationAirport: DestinationAirport,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kidsPrice")
    val kidsPrice: Int,
    @SerializedName("originAirport")
    val originAirport: OriginAirport,
    @SerializedName("plane")
    val plane: Plane,
    @SerializedName("taxPrice")
    val taxPrice: Int
)