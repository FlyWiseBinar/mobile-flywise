package com.binar.projekakhir.model.filterprice


import com.google.gson.annotations.SerializedName

data class GetFilterPriceResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("statue")
    val statue: Boolean
)