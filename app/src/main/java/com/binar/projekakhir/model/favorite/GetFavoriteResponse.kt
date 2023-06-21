package com.binar.projekakhir.model.favorite


import com.google.gson.annotations.SerializedName

data class GetFavoriteResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)