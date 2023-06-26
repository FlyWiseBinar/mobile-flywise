package com.binar.projekakhir.model.history


import com.google.gson.annotations.SerializedName

data class Class(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)