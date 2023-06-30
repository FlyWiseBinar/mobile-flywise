package com.binar.projekakhir.model.checkoutrequest


import com.google.gson.annotations.SerializedName

data class GetCheckoutRequest(
    @SerializedName("passenger")
    val passenger: List<Passenger>,
    @SerializedName("schedule")
    val schedule: List<Schedule>
)