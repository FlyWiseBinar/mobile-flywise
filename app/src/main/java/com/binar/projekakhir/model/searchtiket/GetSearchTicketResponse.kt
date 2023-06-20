package com.binar.projekakhir.model.searchtiket


import com.google.gson.annotations.SerializedName

data class GetSearchTicketResponse(
    @SerializedName("schedule")
    val schedule: List<Schedule>
)