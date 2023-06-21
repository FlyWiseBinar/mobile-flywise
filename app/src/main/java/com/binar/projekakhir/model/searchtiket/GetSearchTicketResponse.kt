package com.binar.projekakhir.model.searchtiket


import com.google.gson.annotations.SerializedName

data class GetSearchTicketResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("statue")
    val statue: Boolean
)