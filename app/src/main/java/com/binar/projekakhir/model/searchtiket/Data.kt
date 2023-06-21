package com.binar.projekakhir.model.searchtiket


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("adultPrice")
    val adultPrice: Int,
    @SerializedName("arrivedDate")
    val arrivedDate: String,
    @SerializedName("arrivedDateTime")
    val arrivedDateTime: String,
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
    @SerializedName("departureDateTime")
    val departureDateTime: String,
    @SerializedName("departureTime")
    val departureTime: String,
    @SerializedName("destinationAirport")
    val destinationAirport: DestinationAirport,
    @SerializedName("durationInSecond")
    val durationInSecond: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kidsPrice")
    val kidsPrice: Int,
    @SerializedName("originAirport")
    val originAirport: OriginAirport,
    @SerializedName("plane")
    val plane: Plane,
    @SerializedName("provTotalPrice")
    val provTotalPrice: Int,
    @SerializedName("taxPrice")
    val taxPrice: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)