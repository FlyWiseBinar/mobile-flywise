package com.binar.projekakhir.model.favorite


import com.google.gson.annotations.SerializedName

data class GetFavoriteResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("schedule")
    val schedule: List<Schedule>
)