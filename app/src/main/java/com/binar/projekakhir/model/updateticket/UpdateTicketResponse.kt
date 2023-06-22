package com.binar.projekakhir.model.updateticket


import com.google.gson.annotations.SerializedName

data class UpdateTicketResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("statue")
    val statue: Boolean
)