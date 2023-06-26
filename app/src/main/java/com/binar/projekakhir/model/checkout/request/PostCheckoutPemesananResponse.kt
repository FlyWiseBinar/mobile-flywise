package com.binar.projekakhir.model.checkout.request


import com.google.gson.annotations.SerializedName

data class PostCheckoutPemesananResponse(
    @SerializedName("passenger")
    val passenger: List<Passenger>,
    @SerializedName("schedule")
    val schedule: List<Schedule>
)