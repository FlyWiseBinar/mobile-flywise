package com.binar.projekakhir.model.detail


import com.google.gson.annotations.SerializedName

data class GetResponseFindSchedulebyId(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("statue")
    val statue: Boolean
)